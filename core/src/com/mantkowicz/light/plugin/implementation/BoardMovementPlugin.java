package com.mantkowicz.light.plugin.implementation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mantkowicz.light.actor.implementation.player.Player;
import com.mantkowicz.light.board.path.BoardPath;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.configuration.api.BoardMovementPluginConfiguration;
import com.mantkowicz.light.plugin.Plugin;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.implementation.TileTouchedEvent;

import static com.mantkowicz.light.actor.implementation.player.Status.IDLE;
import static com.mantkowicz.light.actor.implementation.player.Status.MOVEMENT;
import static com.mantkowicz.light.board.tile.TilePoint.CENTER;
import static com.mantkowicz.light.service.event.GameEventType.TILE_TOUCHED;

public class BoardMovementPlugin implements Plugin {
    private final Player player;
    private final GameEventService gameEventService;
    private final BoardService boardService;
    private final float speed;

    public BoardMovementPlugin(Player player, float speed, BoardMovementPluginConfiguration configuration) {
        this.player = player;
        this.gameEventService = configuration.getGameEventService();
        this.boardService = configuration.getBoardService();
        this.speed = speed;
    }

    @Override
    public void run(float delta) {
        if (gameEventService.containsEvent(TILE_TOUCHED) && IDLE.equals(player.getStatus())) {
            TileTouchedEvent gameEvent = gameEventService.removeEventFromQueue(TILE_TOUCHED, TileTouchedEvent.class);
            player.addAction(getMoveByPathAction(gameEvent));
        }
    }

    private SequenceAction getMoveByPathAction(TileTouchedEvent gameEvent) {
        removePlayerActions(player);
        SequenceAction sequence = Actions.sequence();
        BoardPath path = boardService.getPath(player.getTile(), gameEvent.getEventObject());
        for (Tile pathNode : path.getPathNodes()) {
            Vector2 position = pathNode.calculatePositionForActorAt(player, CENTER);
            SequenceAction moveToTileAction = Actions.sequence(
                    getSetStatusMovementAction(player),
                    Actions.moveTo(position.x, position.y, speed, Interpolation.sineOut),
                    getSetTileAction(path, pathNode),
                    getSetStatusIdleAction(player)
            );
            sequence.addAction(moveToTileAction);
        }
        return sequence;
    }

    private void removePlayerActions(Player player) {
        for (Action action : player.getActions()) {
            player.removeAction(action);
        }
    }

    private Action getSetStatusIdleAction(final Player player) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                player.setStatus(IDLE);
                return true;
            }
        };
    }

    private Action getSetStatusMovementAction(final Player player) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                player.setStatus(MOVEMENT);
                return true;
            }
        };
    }

    private Action getSetTileAction(final BoardPath path, final Tile tile) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                if (path.exists()) {
                    player.setTile(tile);
                }
                return true;
            }
        };
    }
}

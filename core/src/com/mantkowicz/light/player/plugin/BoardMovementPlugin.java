package com.mantkowicz.light.player.plugin;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mantkowicz.light.board.path.BoardPath;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.player.Player;
import com.mantkowicz.light.service.event.GameEvent;
import com.mantkowicz.light.service.event.GameEventService;

import static com.mantkowicz.light.player.PlayerStatus.IDLE;
import static com.mantkowicz.light.player.PlayerStatus.MOVEMENT;
import static com.mantkowicz.light.service.event.type.GameEventType.TILE_TOUCHED;

public class BoardMovementPlugin extends Plugin {
    private Player player;
    private GameEventService gameEventService;
    private BoardService boardService;

    public BoardMovementPlugin(Player player, GameEventService gameEventService, BoardService boardService) {
        this.player = player;
        this.gameEventService = gameEventService;
        this.boardService = boardService;
    }

    @Override
    public void run() {
        if (gameEventService.containsEvent(TILE_TOUCHED) && IDLE.equals(player.getStatus())) {
            GameEvent gameEvent = gameEventService.getEvent(TILE_TOUCHED, true);
            player.addAction(getMoveByPathAction(gameEvent));
        }
    }

    private SequenceAction getMoveByPathAction(GameEvent gameEvent) {
        removePlayerActions(player);
        SequenceAction sequence = Actions.sequence();
        BoardPath path = boardService.getPath(player.getTile(), gameEvent.getTile());

        for (Tile pathNode : path.getPathNodes()) {
            Vector2 position = pathNode.calculatePositionForCenteredActor(player);
            SequenceAction moveToTileAction = Actions.sequence(
                    getSetStatusMovementAction(player),
                    Actions.moveTo(position.x, position.y, player.getSpeed(), Interpolation.sineOut),
                    getSetTileAction(path, pathNode),
                    getSetStatusIdleAction(player)
            );
            sequence.addAction(moveToTileAction);
        }

        return sequence;
    }

    private void removePlayerActions(Player player) {
        for(Action action : player.getActions()) {
            player.removeAction(action);
        }
    }

    private Action getSetStatusIdleAction(Player player) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                player.setStatus(IDLE);
                return true;
            }
        };
    }

    private Action getSetStatusMovementAction(Player player) {
        return new Action() {
            @Override
            public boolean act(float delta) {
                player.setStatus(MOVEMENT);
                return true;
            }
        };
    }

    private Action getSetTileAction(BoardPath path, Tile tile) {
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

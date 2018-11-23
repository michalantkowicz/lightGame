package com.mantkowicz.light.player.plugin;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mantkowicz.light.board.path.BoardPath;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.player.Player;
import com.mantkowicz.light.service.event.GameEvent;
import com.mantkowicz.light.service.event.GameEventService;
import com.mantkowicz.light.service.event.type.GameEventType;

import java.util.Collections;
import java.util.List;

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
        if(gameEventService.containsEvent(TILE_TOUCHED)) {
            GameEvent gameEvent = gameEventService.getEvent(TILE_TOUCHED);
            Tile tile = gameEvent.getTile();
            BoardPath path = boardService.getPath(player.getTile(), tile);
            SequenceAction sequence = Actions.sequence();
            List<Tile> pathNodes = path.getPath();
            for(Tile pathNode : pathNodes) {
                Vector2 position = new Vector2(pathNode.getX() + pathNode.getWidth() / 2f - player.getWidth() / 2f,
                        pathNode.getY() + pathNode.getHeight() / 2f - player.getHeight() / 2f);
                MoveToAction moveTo = Actions.moveTo(position.x, position.y, 0.25f, Interpolation.sineOut);
                sequence.addAction(moveTo);
            }
            sequence.addAction(new Action() {
                @Override
                public boolean act(float delta) {
                    if(path.exists()) {
                        player.setTile(tile);
                    }
                    return true;
                }
            });
            player.addAction(sequence);
        }
    }
}

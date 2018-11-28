package com.mantkowicz.light.board.service;

import com.mantkowicz.light.actor.GameActor;
import com.mantkowicz.light.board.Board;
import com.mantkowicz.light.board.path.BoardPath;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.service.event.GameEvent;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.*;

import static com.mantkowicz.light.service.event.type.GameEventType.COLLISION;

public class BoardService {
    private final Board board;
    private final GameActorsByTile gameActorsByTile;
    private final GameEventService gameEventService;

    public BoardService(GameEventService gameEventService, Board board) {
        this.gameEventService = gameEventService;
        this.board = board;

        this.gameActorsByTile = new GameActorsByTile(board.getTiles());
    }

    public void registerGameActor(Tile tile, GameActor gameActor) {
        gameActorsByTile.putGameActor(tile, gameActor);
        checkCollisions(tile, gameActor);
    }

    private void checkCollisions(Tile tile, GameActor gameActor) {
        for (GameActor tileActor : gameActorsByTile.getGameActors(tile)) {
            if (!tileActor.equals(gameActor)) {
                gameEventService.addEvent(new GameEvent(COLLISION, tile, tileActor));
            }
        }
    }

    public void unregisterGameActor(Tile tile, GameActor gameActor) {
        gameActorsByTile.removeGameActor(tile, gameActor);
    }

    public BoardPath getPath(Tile startTile, Tile endTile) {
        List<Tile> result = new ArrayList<>();
        Map<Tile, Tile> prev = new HashMap<>();
        Map<Tile, Integer> lengthMap = getLengthMap(startTile);
        List<Tile> Q = new ArrayList<>(lengthMap.keySet());

        while (!Q.isEmpty()) {
            Tile tile = getNearest(lengthMap, Q);
            if (tile.isAccessible()) {
                for (Tile neighbour : tile.getNeighbours()) {
                    if (neighbour.isAccessible()) {
                        if (lengthMap.get(tile) + 1 < lengthMap.get(neighbour)) {
                            lengthMap.put(neighbour, lengthMap.get(tile) + 1);
                            prev.put(neighbour, tile);
                        }
                    }
                }
            }
        }

        if (lengthMap.get(endTile) < Integer.MAX_VALUE) {
            while (prev.get(endTile) != null) {
                result.add(endTile);
                endTile = prev.get(endTile);
            }
        }

        Collections.reverse(result);
        return new BoardPath(result);
    }

    private Map<Tile, Integer> getLengthMap(Tile startTile) {
        Map<Tile, Integer> lengthMap = new HashMap<>();
        for (Tile tile : board.getTiles()) {
            lengthMap.put(tile, Integer.MAX_VALUE);
        }
        lengthMap.put(startTile, 0);
        return lengthMap;
    }

    private Tile getNearest(Map<Tile, Integer> lengthMap, List<Tile> Q) {
        int index = 0;
        for (int i = 0; i < Q.size(); i++) {
            if (lengthMap.get(Q.get(i)) < lengthMap.get(Q.get(index))) {
                index = i;
            }
        }
        return Q.remove(index);
    }
}

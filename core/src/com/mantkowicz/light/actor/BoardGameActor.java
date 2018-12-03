package com.mantkowicz.light.actor;

import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;

public abstract class BoardGameActor extends GameActor {
    private final BoardService boardService;
    private Tile tile;

    protected BoardGameActor(GameActorType gameActorType, BoardService boardService) {
        super(gameActorType);
        this.boardService = boardService;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        if (this.tile != null) {
            boardService.unregisterGameActor(this.tile, this);
        }

        this.tile = tile;

        boardService.registerGameActor(tile, this);

        Vector2 position = tile.calculatePositionForCenteredActor(this);
        this.setPosition(position.x, position.y);
    }

    @Override
    public boolean remove() {
        if (this.tile != null) {
            boardService.unregisterGameActor(this.tile, this);
        }
        return super.remove();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BoardGameActor that = (BoardGameActor) o;

        if (boardService != null ? !boardService.equals(that.boardService) : that.boardService != null) return false;
        return getTile() != null ? getTile().equals(that.getTile()) : that.getTile() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (boardService != null ? boardService.hashCode() : 0);
        result = 31 * result + (getTile() != null ? getTile().hashCode() : 0);
        return result;
    }
}

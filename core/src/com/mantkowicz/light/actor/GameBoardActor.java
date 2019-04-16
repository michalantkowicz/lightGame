package com.mantkowicz.light.actor;

import com.badlogic.gdx.math.Vector2;
import com.mantkowicz.light.board.service.BoardService;
import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.interaction.DescriptionInteraction;
import com.mantkowicz.light.interaction.Interaction;
import com.mantkowicz.light.interaction.Interactive;
import com.mantkowicz.light.service.event.GameEventService;

import java.util.ArrayList;
import java.util.List;

import static com.mantkowicz.light.board.tile.TilePoint.CENTER;

public abstract class GameBoardActor extends GameActor implements Interactive {
    private final List<Interaction> interactions = new ArrayList<>();
    private final BoardService boardService;
    private final GameEventService gameEventService;
    private Tile tile;

    protected GameBoardActor(GameActorType gameActorType, BoardService boardService, GameEventService gameEventService) {
        super(gameActorType);
        this.boardService = boardService;
        this.gameEventService = gameEventService;

        interactions.add(new DescriptionInteraction(null, this, getDescription()));
        interactions.add(new DescriptionInteraction(null, this, getDescription()));
        interactions.add(new DescriptionInteraction(null, this, getDescription()));
        addListener(new GameBoardActorListener(this, gameEventService));
    }

    protected abstract String getDescription();

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        if (this.tile != null) {
            boardService.unregisterGameActor(this.tile, this);
        }

        this.tile = tile;

        boardService.registerGameActor(tile, this);

        Vector2 position = tile.calculatePositionForActorAt(this, CENTER);
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

        GameBoardActor that = (GameBoardActor) o;

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

    @Override
    public List<Interaction> getInteractions() {
        return interactions;
    }
}

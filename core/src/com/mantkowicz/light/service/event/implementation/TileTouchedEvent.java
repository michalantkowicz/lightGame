package com.mantkowicz.light.service.event.implementation;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.service.event.GameEvent;

import static com.mantkowicz.light.service.event.GameEventType.TILE_TOUCHED;

public class TileTouchedEvent extends GameEvent<Tile> {
    public TileTouchedEvent(Tile tile) {
        super(TILE_TOUCHED, tile);
    }
}

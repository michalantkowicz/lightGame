package com.mantkowicz.light.map;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.util.Tuple;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TileSet {
    private List<Tile> tiles;
    private TilesetProperties properties;

    @Data
    public static class TilesetProperties {
        private Tuple<Float, Float> tileSize;
    }
}

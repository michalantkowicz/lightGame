package com.mantkowicz.light.map;

import com.mantkowicz.light.board.tile.Tile;
import com.mantkowicz.light.util.Tuple;

import java.util.List;

public class TileSet {
    private List<Tile> tiles;
    private TilesetProperties properties;

    TileSet(List<Tile> tiles, TilesetProperties properties) {
        this.tiles = tiles;
        this.properties = properties;
    }

    public static TileSetBuilder builder() {
        return new TileSetBuilder();
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public TilesetProperties getProperties() {
        return this.properties;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public void setProperties(TilesetProperties properties) {
        this.properties = properties;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TileSet)) return false;
        final TileSet other = (TileSet) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$tiles = this.getTiles();
        final Object other$tiles = other.getTiles();
        if (this$tiles == null ? other$tiles != null : !this$tiles.equals(other$tiles)) return false;
        final Object this$properties = this.getProperties();
        final Object other$properties = other.getProperties();
        if (this$properties == null ? other$properties != null : !this$properties.equals(other$properties))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TileSet;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tiles = this.getTiles();
        result = result * PRIME + ($tiles == null ? 43 : $tiles.hashCode());
        final Object $properties = this.getProperties();
        result = result * PRIME + ($properties == null ? 43 : $properties.hashCode());
        return result;
    }

    public String toString() {
        return "TileSet(tiles=" + this.getTiles() + ", properties=" + this.getProperties() + ")";
    }

    public static class TilesetProperties {
        private Tuple<Float, Float> tileSize;

        public TilesetProperties() {
        }

        public Tuple<Float, Float> getTileSize() {
            return this.tileSize;
        }

        public void setTileSize(Tuple<Float, Float> tileSize) {
            this.tileSize = tileSize;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof TilesetProperties)) return false;
            final TilesetProperties other = (TilesetProperties) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$tileSize = this.getTileSize();
            final Object other$tileSize = other.getTileSize();
            if (this$tileSize == null ? other$tileSize != null : !this$tileSize.equals(other$tileSize)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof TilesetProperties;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $tileSize = this.getTileSize();
            result = result * PRIME + ($tileSize == null ? 43 : $tileSize.hashCode());
            return result;
        }

        public String toString() {
            return "TileSet.TilesetProperties(tileSize=" + this.getTileSize() + ")";
        }
    }

    public static class TileSetBuilder {
        private List<Tile> tiles;
        private TilesetProperties properties;

        TileSetBuilder() {
        }

        public TileSet.TileSetBuilder tiles(List<Tile> tiles) {
            this.tiles = tiles;
            return this;
        }

        public TileSet.TileSetBuilder properties(TilesetProperties properties) {
            this.properties = properties;
            return this;
        }

        public TileSet build() {
            return new TileSet(tiles, properties);
        }

        public String toString() {
            return "TileSet.TileSetBuilder(tiles=" + this.tiles + ", properties=" + this.properties + ")";
        }
    }
}

package com.mantkowicz.light.map.implementation.tmx;

public class TmxTileMapLoaderProperties {
    private String tileMapFileName;

    public String getTileMapFileName() {
        return tileMapFileName;
    }

    public TmxTileMapLoaderProperties setTileMapFileName(String tileMapFileName) {
        this.tileMapFileName = tileMapFileName;
        return this;
    }
}

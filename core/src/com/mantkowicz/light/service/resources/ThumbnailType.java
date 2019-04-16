package com.mantkowicz.light.service.resources;

public enum ThumbnailType {
    DEFAULT("collectButton");

    private final String regionName;

    ThumbnailType(String regionName) {
        this.regionName = regionName;
    }

    String getRegionName() {
        return regionName;
    }
}

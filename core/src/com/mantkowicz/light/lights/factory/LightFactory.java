package com.mantkowicz.light.lights.factory;

import box2dLight.RayHandler;
import com.mantkowicz.light.lights.GameLight;
import com.mantkowicz.light.lights.LightType;
import com.mantkowicz.light.lights.implementation.TorchLight;

public class LightFactory {
    public static GameLight createLight(RayHandler rayHandler, LightType lightType) {
        switch (lightType) {
            case TORCH:
                return new TorchLight(rayHandler);
        }
        throw new IllegalArgumentException("No implementation for lightType " + String.valueOf(lightType));
    }
}

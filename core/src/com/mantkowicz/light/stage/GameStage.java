package com.mantkowicz.light.stage;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mantkowicz.light.service.resources.ResourcesService;

import static com.mantkowicz.light.service.resources.ShaderType.FADE_OUT;

public class GameStage extends Stage {
    private final ShaderProgram shaderProgram;

    public GameStage(Viewport viewport, ResourcesService resourcesService) {
        super(viewport);
        shaderProgram = new ShaderProgram(resourcesService.getShaderVertex(FADE_OUT), resourcesService.getShaderFragment(FADE_OUT));
    }

    public void fadeOut() {
        shaderProgram.begin();
        shaderProgram.setUniformf("saturation", 0.25f);
        shaderProgram.setUniformf("brightness", 0.25f);
        shaderProgram.end();

        getBatch().setShader(shaderProgram);
    }

    public void fadeIn() {
        getBatch().setShader(null);
    }
}

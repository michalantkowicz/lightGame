package com.mantkowicz.light.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mantkowicz.light.game.Main;

import static com.mantkowicz.light.game.Main.SCREEN_HEIGHT;
import static com.mantkowicz.light.game.Main.SCREEN_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) SCREEN_WIDTH/2;
		config.height = (int) SCREEN_HEIGHT/2;
		System.setProperty("user.name","Michal");
		new LwjglApplication(new Main(), config);
	}
}

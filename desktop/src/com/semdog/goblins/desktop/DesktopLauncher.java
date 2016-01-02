package com.semdog.goblins.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.semdog.goblins.GoblinsGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1800;
		config.height = (int)(config.width * (9.f / 16.f));
		config.resizable = false;
		config.useGL30 = true;
		new LwjglApplication(new GoblinsGame(), config);
	}

}

package com.nukeandjuke.bounce.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nukeandjuke.bounce.BGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bounce";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new BGame(), config);
	}
}

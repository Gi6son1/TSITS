package com.tsits.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tsits.game.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//settings for the game desktop window
		config.setForegroundFPS(60);
		config.setTitle("TSITS");
		config.useVsync(true);
		config.setWindowedMode(1280, 720);

		new Lwjgl3Application(new Game(), config);
	}
}

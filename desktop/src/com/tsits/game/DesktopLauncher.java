package com.tsits.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tsits.screenhandler.Tsits;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("TSITS");

		config.setWindowedMode(1280, 720);

		//Syncs the frames to run at 60fps on all but the crappiest of systems.
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new Tsits(), config);
	}
}

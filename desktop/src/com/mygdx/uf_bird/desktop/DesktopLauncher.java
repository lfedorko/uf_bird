package com.mygdx.uf_bird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.uf_bird.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameMain.W;
		config.height = GameMain.H;
		config.title = GameMain.T;
		new LwjglApplication(new GameMain(), config);
	}
}

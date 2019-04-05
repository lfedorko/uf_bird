package com.mygdx.uf_bird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.uf_bird.MyGame;
import com.mygdx.uf_bird.controller.Launch;
import com.mygdx.uf_bird.view.GameView;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Launch.W;
		config.height = Launch.H;
		config.title = Launch.T;
		new LwjglApplication(new MyGame(), config);
	}
}

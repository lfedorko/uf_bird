package com.mygdx.uf_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.uf_bird.view.GameScreen;
import com.mygdx.uf_bird.view.MainScreen;

public class GameMain extends Game {
	public static final int W = 400;
	public static final int H = 800;
	public static final String T = "uf_bird";
	public MainScreen main;
	public GameScreen game;


	@Override
	public void create () {

		main = new MainScreen(this);
		game = new GameScreen(this);

		setScreen(main);
	}
}

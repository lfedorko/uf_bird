package com.mygdx.uf_bird.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.uf_bird.model.World;

public class GameView {

	private TextureAtlas texture;
	public static SpriteBatch batch;
	private World world;
	private Texture labelUF;
	private Texture labelGame;
	private Texture labelOver;
	private Texture overBanner;
	private Texture medal;
	private BitmapFont font;


	public GameView(World world) {
		this.world = world;
		batch = new SpriteBatch();
		texture = new TextureAtlas("test.atlas");
		labelUF = new Texture("Banner.png");
		medal = new Texture("m3.png");
		labelGame = new Texture("game.png");
		labelOver = new Texture("over.png");
		overBanner = new Texture("over_banner.png");
		font = new BitmapFont(Gdx.files.internal("fonts/main.fnt"));


	}

	public void dispose(){
		medal.dispose();
		texture.dispose();
		batch.dispose();
		labelGame.dispose();
		labelOver.dispose();
		overBanner.dispose();
		medal.dispose();
		font.dispose();
		labelUF.dispose();
	}

	public void render() {

		batch.begin();
		if (world.getState() == "START")
			startRender();
		else if (world.getState() == "GAME")
			gameRender();
		else if (world.getState() == "OVER")
			overRender();
		batch.end();


	}

	private void gameRender() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.getGround().draw();
		world.getPipes().draw();
		world.getBird().draw();
		font.getData().setScale(2.0f);
		font.draw(batch, Integer.toString(world.getPipes().getScore()), 10, Gdx.graphics.getHeight() - 10);
	}

	private void startRender() {
		world.getGround().draw();
		world.getStartButton().draw();
		batch.draw(labelUF, 50, Gdx.graphics.getHeight() / 4 * 2.5f, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 5);
	}

	private void overRender() {
		world.getGround().draw();
		world.getPipes().draw();
		world.getBird().draw();
		batch.draw(labelGame, 5, Gdx.graphics.getHeight() / 4 * 2.5f, Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() / 5);
		batch.draw(labelOver, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4 * 2.5f, Gdx.graphics.getWidth() / 2 - 10, Gdx.graphics.getHeight() / 5);
		batch.draw(overBanner, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 5);
		batch.draw(new Texture("m" + world.getMedal() + ".png"),Gdx.graphics.getWidth() / 3.375f , Gdx.graphics.getHeight() / 3.46f, Gdx.graphics.getWidth() / 9, Gdx.graphics.getHeight() / 12);
		font.getData().setScale(0.7f);
		font.draw(batch, Integer.toString(world.getuserScore()), Gdx.graphics.getWidth() / 8 * 5, Gdx.graphics.getHeight() / 8 * 3.1f);
		font.draw(batch, Integer.toString(world.getmaxScore()), Gdx.graphics.getWidth() / 8 * 5, Gdx.graphics.getHeight() / 8 * 2.5f);
		world.getRestartButton().draw();
	}

}

package com.mygdx.uf_bird.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.uf_bird.model.World;

public class GameView {

	private TextureAtlas texture;
	public static SpriteBatch batch;
	private World world;
	private Texture label;
	private BitmapFont font;



	public GameView(World world) {
		this.world = world;
		batch = new SpriteBatch();
		texture = new TextureAtlas("test.atlas");
		label = new Texture("Banner.png");
		font = new BitmapFont(Gdx.files.internal("fonts/main.fnt"));


	}

	public void render(){

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
		world.getGround().draw();
		world.getPipes().draw();
		world.getBird().draw();
		font.draw(batch, Integer.toString(world.getPipes().getScore()),10 ,Gdx.graphics.getHeight() - 10);
	}

	private void startRender()
	{

		world.getGround().draw();
		world.getStartButton().draw();
		batch.draw(label, 50, Gdx.graphics.getHeight()/4 * 2.5f,Gdx.graphics.getWidth() - 100 ,Gdx.graphics.getHeight()/5);
	}

	private void overRender()
	{
		world.getGround().draw();

	}




}

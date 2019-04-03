package com.mygdx.uf_bird.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.uf_bird.GameMain;
import com.mygdx.uf_bird.model.Button;


public class GameScreen implements Screen, InputProcessor {

    private TextureAtlas texture;
    private SpriteBatch batch;
    private Texture button;
    private TextureRegion background;
    private GameMain game;
    private Button start;
    private Boolean isClicked;
    private BitmapFont font;


    public GameScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        texture = new TextureAtlas(Gdx.files.internal("flappy.txt"));
        background = texture.findRegion("bg1");
        batch = new SpriteBatch();
        button = new Texture("playbtn.png");
        start = new Button(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/4,"playbtn.png");
        isClicked = false;
        font = new BitmapFont(Gdx.files.internal("fonts/flappy.fnt"));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(start.getBtn(),start.getX(),start.getY(), start.getW(), start.getH());
        font.draw(batch,"1234567", 200,200);
        batch.end();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    //обработка нажатия
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
//
//        if (start.isClicked(x,y))
//            isClicked = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        if(isClicked){
//            game.setScreen(game.main);
//            isClicked = false;
//        }
        return true;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();

    }

}

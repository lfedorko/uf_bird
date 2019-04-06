package com.mygdx.uf_bird.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.mygdx.uf_bird.model.World;
import com.mygdx.uf_bird.view.GameView;

public class Launch implements Screen, InputProcessor {
    private World world;
    private GameView renderer;
    private GameController controller;

    public static final int W = 540;
    public static final int H = 900;
    public static final String T = "uf_bird";

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.touchDown(screenX,screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        controller.touchUp();
        return false;
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
    public void show() {
        world = new World();
        renderer = new GameView(world);
        controller = controller.getInstance(world);

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        controller.update();
        renderer.render();

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
        dispose();

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        renderer.dispose();
        world.dispose();
    }
}

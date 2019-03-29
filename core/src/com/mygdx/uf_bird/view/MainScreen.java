package com.mygdx.uf_bird.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.uf_bird.GameMain;
import com.mygdx.uf_bird.model.Bird;
import com.badlogic.gdx.math.Circle;
import com.mygdx.uf_bird.model.Pipe;
import com.mygdx.uf_bird.model.PipeCollector;

import java.util.TreeMap;

public class MainScreen implements Screen {


    private TextureAtlas texture;
    private SpriteBatch batch;
    private ShapeRenderer render;
    private Bird bird;
    private PipeCollector pipes;
    private  TextureRegion birdSprite;
    private  TextureRegion bgSprite;
    private  TextureRegion floorSprite;
    private  TextureRegion topSprite;
    private  TextureRegion botSprite;

//    переключение на другой экран
    @Override
    public void show() {

        texture = new TextureAtlas(Gdx.files.internal("mainGame.txt"));
        birdSprite = texture.findRegion("blue1");
        bgSprite = texture.findRegion("bg1");
        floorSprite = texture.findRegion("spr_earth");
        topSprite = texture.findRegion("tile000");
        botSprite = texture.findRegion("tile001");
        batch = new SpriteBatch();
        bird = new Bird(100f,500f, 1.0f);
        pipes = new PipeCollector();
        render = new ShapeRenderer();

    }
    // время между кадрами в секунду
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateWorld(delta);

        renderDebug();


    }

    @Override
    public void pause(){}

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }
    //переключение на другой экран
    @Override
    public void hide() {

    }
    //закрытие уничтожение ресурсов
    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        render.dispose();
    }


    private void renderDebug()
    {
        Rectangle birdCircle = bird.getColissionRectangle();
        render.begin(ShapeRenderer.ShapeType.Line);
        render.rect(birdCircle.x, birdCircle.y, birdCircle.width, birdCircle.height);

        for (Pipe pipe : pipes.getPipes())
        {

            Rectangle bottomPipe = pipe.getBotomPipe();
            Rectangle topPipe = pipe.getTopPipe();
            render.rect(bottomPipe.x, bottomPipe.getY(),bottomPipe.getWidth(),bottomPipe.getHeight());
            render.rect(topPipe.x, topPipe.getY(), topPipe.getWidth(), topPipe.getHeight());
        }
        render.end();
        batch.begin();
//        for (Pipe pipe : pipes.getPipes())
//        {
//            batch.draw(botSprite, pipe.getBotomPipe().x,pipe.getBotomPipe().getY(), pipe.getBotomPipe().getWidth(), pipe.getBotomPipe().getHeight());
//        }
//        batch.draw(bgSprite, 0,0, GameMain.W, GameMain.H);
        bird.draw(batch);
        batch.end();


    }

    private void updateWorld(float delta)
    {
        //background update
        if (!bird.isDead())
        {
            pipes.update();
            bird.update();

            if (Gdx.input.justTouched())
                bird.jump();
            if (pipes.сheckOverlap(bird.getColissionRectangle()))
                bird.setDead(true);
//        if (bird.is_bird_dead())
//            bird.setY(0);
        }
        else
        {
            bird.setRotate(270);
            bird.setGravity(-4);
            bird.update();
        }
    }


}

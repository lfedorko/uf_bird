package com.mygdx.uf_bird.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.uf_bird.GameMain;
import com.mygdx.uf_bird.model.Bird;
import com.mygdx.uf_bird.model.Ground;
import com.mygdx.uf_bird.model.Pipe;
import com.mygdx.uf_bird.model.PipeCollector;

import sun.applet.Main;

public class MainScreen implements Screen {


    private TextureAtlas texture;
    private SpriteBatch batch;
    private ShapeRenderer render;
    private Bird bird;
    private Ground ground;
    private PipeCollector pipes;
    private  TextureRegion bgSprite;
    private  TextureRegion floorSprite;
    private  TextureRegion base;
    private  TextureRegion topPipeS;
    private GameMain game;
    private BitmapFont font;


//    переключение на другой экран


    public MainScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        texture = new TextureAtlas(Gdx.files.internal("flappy.txt"));
//        birdSprite = texture.findRegion("blue1");
        bgSprite = texture.findRegion("bg1");
        floorSprite = texture.findRegion("base");
        topPipeS = texture.findRegion("cap");
        base = texture.findRegion("base_pipe");
        batch = new SpriteBatch();
        ground = new Ground();
        bird = new Bird();
        pipes = new PipeCollector();
        render = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("fonts/flappy.fnt"));

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
//        Rectangle birdCircle = bird.getColissionRectangle();
//        render.begin(ShapeRenderer.ShapeType.Line);
//        render.rect(birdCircle.x, birdCircle.y, birdCircle.width, birdCircle.height);

//        for (Pipe pipe : pipes.getPipes())
//        {
//
//            Rectangle bottomPipe = pipe.getBotomPipe();
//            Rectangle topPipe = pipe.getTopPipe();
//           render.rect(bottomPipe.x, bottomPipe.getY(),bottomPipe.getWidth(),bottomPipe.getHeight());
//            render.rect(topPipe.x, topPipe.getY(), topPipe.getWidth(), topPipe.getHeight());
//        }
//        render.end();
        batch.begin();

        batch.draw(bgSprite, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        for (Pipe pipe : pipes.getPipes())
        {
            Rectangle botomPipe = pipe.getBotomPipe();
            Rectangle topPipe = pipe.getTopPipe();

            batch.draw(base, topPipe.x, topPipe.getY(), topPipe.getWidth(), topPipe.getHeight());
            batch.draw(topPipeS, topPipe.x, topPipe.getY(), topPipe.getWidth(), 25);
            batch.draw(base, botomPipe.x, botomPipe.y, botomPipe.width, botomPipe.height);
            batch.draw(topPipeS, botomPipe.x, botomPipe.y + botomPipe.height - 24, botomPipe.width, 25);
            batch.draw(floorSprite, ground.getFirstPos(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 800 * 100);
            batch.draw(floorSprite, ground.getSecondPos(), 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 800 * 100);
            font.draw(batch, Integer.toString(pipes.getScore()),10 ,Gdx.graphics.getHeight() - 10);
        }
        bird.draw(batch);
        batch.end();


    }

    private void updateWorld(float delta)
    {
        //background update
        if (!bird.isDead()) {
            pipes.update();
            bird.update();
            ground.update();
            if (Gdx.input.justTouched())
                bird.jump();
            if (pipes.сheckOverlap(bird.getColissionRectangle()))
            {
                bird.setDead(true);
            }
        }
        else
        {
            bird.update();
        }

    }


}

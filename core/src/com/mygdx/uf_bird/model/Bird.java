package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.uf_bird.view.GameView;

public class Bird implements ObjectToDraw {

    private Rectangle collisionRectangle;
    private float x;
    private float y;
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;
    private final static float WIDTH = Gdx.graphics.getWidth() / 10;
    private final static float HEIGHT = Gdx.graphics.getWidth() / 10;
    private TextureAtlas birdAtlas;
    private Animation<TextureAtlas.AtlasRegion> birdAnimation;
    private TextureRegion deadBird;
    private float timePassed = 0;
    private float rotate;
    private float gravity;
    private ShapeRenderer render;
    private boolean isOnGround;

    public boolean isJumped() {
        return isJumped;
    }

    private boolean isDead;
    private boolean isJumped;
    public boolean isClockwise;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }


    public void setJumped(boolean jumped) {
        isJumped = jumped;
    }

    public float getX() {
        return x;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public Bird() {
        this.x = Gdx.graphics.getWidth() / 2 - WIDTH;
        this.y = Gdx.graphics.getHeight() / 2;
        this.collisionRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        isDead = false;
        gravity = - Gdx.graphics.getHeight() / 200;
        rotate = 0;
        birdAtlas = new TextureAtlas(Gdx.files.internal("red.atlas"));
        birdAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, birdAtlas.getRegions());
        deadBird = birdAtlas.findRegion("yellow1");
        isClockwise = true;
        isJumped = false;
        isOnGround = false;
        render = new ShapeRenderer();
    }

    public void reset(){
        this.x = Gdx.graphics.getWidth() / 2 - WIDTH;
        this.y = Gdx.graphics.getHeight() / 2;
        gravity =- Gdx.graphics.getHeight() / 200;
        rotate = 0;
        isJumped = false;
        isOnGround = false;
        isDead = false;
    }

    public void setY(float y) {
        this.y = y;
        this.collisionRectangle.setY(y);
    }


    public void update() {
        float off = (int) (rotate * HEIGHT / 100 + (0.1f * HEIGHT));
        if (y > GROUND - off)
            setY(y + gravity);
        else {
            setY(GROUND - off);
            isOnGround = true;
        }

    }

    @Override
    public void draw() {

        if (!isDead) {
            timePassed += Gdx.graphics.getDeltaTime();
            GameView.batch.draw(birdAnimation.getKeyFrame(timePassed, true), collisionRectangle.getX(), collisionRectangle.getY(), WIDTH, HEIGHT, WIDTH, HEIGHT, 1f, 1f, -rotate);
        } else {
            if (rotate <= 90 && y > 2 * GROUND) {
                gravity *= 1.1;
                rotate += 2;
            } else if (rotate < 90)
                rotate = 90;

            GameView.batch.draw(deadBird, collisionRectangle.getX(), collisionRectangle.getY(), WIDTH, HEIGHT, WIDTH, HEIGHT, 1f, 1f, -rotate);
            render.end();

        }
    }

    public void jump() {
        setY(y + Gdx.graphics.getHeight() /9);
        isJumped = false;
    }

    public Rectangle getColissionRectangle() {
        return collisionRectangle;
    }

    public void dispose()
    {
        birdAtlas.dispose();
        render.dispose();
    }

}

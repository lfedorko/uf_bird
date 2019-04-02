package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;

import java.util.SortedMap;


public class Bird implements Object{

    private final Rectangle collisionRectangle;
    private float x;
    private float y;
    private float speed;
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;


    public static float getWIDTH() {
        return WIDTH;
    }

    public static float getHEIGHT() {
        return HEIGHT;
    }

    private final static float WIDTH =  2 * 34 * Gdx.graphics.getWidth() / 400;
    private final static float HEIGHT = 2 * 24 * Gdx.graphics.getWidth() / 400;
    private TextureAtlas birdAtlas;
    private Animation<TextureAtlas.AtlasRegion> birdAnimation;
    private TextureRegion deadBird;
    private float timePassed = 0;


    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    private float rotate;
    private float gravity;
    private boolean isDead;
    private Sound flying;



    public boolean isDead() {
        return isDead;
    }

    public float getRotate() {
        return rotate;
    }



    public void setDead(boolean dead) {
        isDead = dead;
        rotate = 270;
        setY(y - HEIGHT);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed-= speed;
    }


    public void setGravity(float gravity) {
        this.gravity = gravity;
    }


    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }

    public Bird(float x, float y, float speed) {
        this.collisionRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        this.x = x;
        this.y = y;
        this.speed = speed;
        isDead = false;
        gravity = -3;
        rotate = 0;
        flying = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));//
        birdAtlas = new TextureAtlas(Gdx.files.internal("red.atlas"));
        birdAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, birdAtlas.getRegions());
        deadBird = birdAtlas.findRegion("yellow1");
    }



    public void setY(float y)
    {
        this.y = y;
        this.collisionRectangle.setY(y);
    }


    public void update() {

        if (y > GROUND)
            setY(y + gravity);
        if (y <= GROUND)
        {
            setY(GROUND);
        }

    }

    public void draw(SpriteBatch batch) {

        if (!isDead) {
            timePassed += Gdx.graphics.getDeltaTime();
            batch.draw(birdAnimation.getKeyFrame(timePassed, true), x, y, WIDTH,HEIGHT);
        }
        else
        {
            setRotate(270);
            batch.draw(deadBird, x, y,WIDTH,HEIGHT,WIDTH,HEIGHT,1,1,rotate);
            timePassed = 0;

        }
    }
    public void setAnimation(){
    }

    public void jump(){
        setY(getY() + 100);
        flying.play();
    }


    public Rectangle getColissionRectangle(){
        return collisionRectangle;
    }
    public void dispose(){}
}

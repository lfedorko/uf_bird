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
    private int score;
    private boolean setClockwise;

    public static float getWIDTH() {
        return WIDTH;
    }

    public static float getHEIGHT() {
        return HEIGHT;
    }

    private final static float WIDTH =  34;
    private final static float HEIGHT = 24;
    private TextureAtlas birdAtlas;
    private Animation<TextureAtlas.AtlasRegion> birdAnimation;
    private TextureRegion deadBird;
    private float timePassed = 0;
    private float rotate;
    private float gravity;
    private boolean isDead;
    private Sound flying;

    public void setClockwise(boolean clockwise) {
        isClockwise = clockwise;
    }

    public boolean isClockwise;



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

    public Bird() {
        this.x = Gdx.graphics.getWidth() / 2 - WIDTH;
        this.y = Gdx.graphics.getWidth() / 2 ;
        this.collisionRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        this.speed = speed;
        isDead = false;
        gravity = -3;
        rotate = 0;
        flying = Gdx.audio.newSound(Gdx.files.internal("music/wing.ogg"));//
        birdAtlas = new TextureAtlas(Gdx.files.internal("red.atlas"));
        birdAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, birdAtlas.getRegions());
        deadBird = birdAtlas.findRegion("yellow1");
        score = 0;
        isClockwise = true;
    }



    public void setY(float y)
    {
        this.y = y;
        this.collisionRectangle.setY(y);
    }

    public void setScore()
    {
        score +=1;
        System.out.println(score);
    }

    public void update() {

        if (y > GROUND)
            setY(y + gravity);
        if (y <= GROUND)
        {
            setY(GROUND );
        }

    }

    public void draw(SpriteBatch batch) {

        if (!isDead) {
            timePassed += Gdx.graphics.getDeltaTime();
            batch.draw(birdAnimation.getKeyFrame(timePassed, true), getColissionRectangle().getX(), getColissionRectangle().getY(),WIDTH,HEIGHT,WIDTH,HEIGHT,Gdx.graphics.getWidth()/400,Gdx.graphics.getHeight()/800,0, true);
        }
        else
        {
//            setRotate(270);
            batch.draw(deadBird, collisionRectangle.getX(), getColissionRectangle().getY(),WIDTH,HEIGHT,WIDTH,HEIGHT,1,1,rotate,isClockwise);
            timePassed = 0;

        }
    }
    public void setAnimation(){
    }

    public void jump(){
        //setRotate(45);
        setClockwise(true);
        setY(y + 100);
        flying.play();
    }


    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public Rectangle getColissionRectangle(){
        return collisionRectangle;
    }
    public void dispose(){}
}

package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.uf_bird.view.GameView;


public class Bird implements ObjectToDraw {

    private Rectangle collisionRectangle;
    private float x;
    private float y;
    private float speed;
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;
    private int score;
    private final static float WIDTH =  Gdx.graphics.getWidth() / 10 ;
    private final static float HEIGHT = Gdx.graphics.getWidth() / 10 ;
    private TextureAtlas birdAtlas;
    private Animation<TextureAtlas.AtlasRegion> birdAnimation;
    private TextureRegion deadBird;
    private float timePassed = 0;
    private float rotate;
    private float gravity;

    public boolean isJumped() {
        return isJumped;
    }

    private boolean isDead;
    private boolean isJumped;
    private SpriteBatch batch;

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

    public void setJumped(boolean jumped) {
        isJumped = jumped;
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }

    public Bird() {
        this.x = Gdx.graphics.getWidth() / 2 - WIDTH;
        this.y = Gdx.graphics.getHeight() / 2 ;
        this.collisionRectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        isDead = false;
        gravity = -3;
        rotate = 0;
        birdAtlas = new TextureAtlas(Gdx.files.internal("red.atlas"));
        birdAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, birdAtlas.getRegions());
        deadBird = birdAtlas.findRegion("yellow1");
        score = 0;
        isClockwise = true;
        isJumped = false;
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

            setY(GROUND );
        }

    }

    @Override
    public void draw() {

        if (!isDead) {
            timePassed += Gdx.graphics.getDeltaTime();
//            System.out.println("Ground" + GROUND +" Bird" + y);
            GameView.batch.draw(birdAnimation.getKeyFrame(timePassed, true),collisionRectangle.getX(),collisionRectangle.getY(),WIDTH,HEIGHT,WIDTH, HEIGHT,1f,1f,rotate );
        }
        else
        {
            if (rotate > -90) {
                rotate -= 1;
                speed +=1;
            }
            collisionRectangle.set(x, y - HEIGHT, WIDTH,HEIGHT);
            GameView.batch.draw(deadBird,collisionRectangle.getX(),collisionRectangle.getY(), WIDTH,HEIGHT,WIDTH, HEIGHT,1f,1f,rotate);
//            System.out.println(collisionRectangle.getX() +" "+ collisionRectangle.getY());
            timePassed = 0;

        }
    }


    public void jump(){
        setY(y + 100);
        isJumped = false;
    }


    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public Rectangle getColissionRectangle(){
        return collisionRectangle;
    }
    public void dispose(){}
}

package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import java.util.SortedMap;


public class Bird implements Object{

    private final Rectangle collisionRectangle;
    private float x;
    private float y;
    private float speed;

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    private float rotate;
    private float gravity;
    private boolean isDead;
    private Sound flying;
    private TextureAtlas texture;
    private Sprite bird;


    public boolean isDead() {
        return isDead;
    }

    public float getRotate() {
        return rotate;
    }



    public void setDead(boolean dead) {
        isDead = dead;
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

    public Bird(float x, float y, float speed)
    {
        this.collisionRectangle = new Rectangle(x, y, 30, 22);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.isDead = false;
        this.gravity = -3;
        this.flying = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));//
        // texture = new TextureAtlas(Gdx.files.internal("mainGame.txt"));
        // bird = texture.createSprite('blue1');
    }



    public void setY(float y)
    {
        this.y = y;
        this.collisionRectangle.setY(y);
    }


    public void update() {

        if (y > 100)
            setY(y + gravity);
        if (y <= 100)
        {
            rotate = 270;
            setY(100);
        }

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

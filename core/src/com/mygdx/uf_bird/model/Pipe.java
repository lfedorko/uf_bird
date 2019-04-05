package com.mygdx.uf_bird.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipe{


    private static final int PIPE_WIDTH = Gdx.graphics.getWidth() / 400 * 52 * 2;
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;
    private static final int SPACE = Gdx.graphics.getHeight() / 800 * 100;
    private static final int GAP = Gdx.graphics.getHeight() / 800 * 200;
    private Rectangle collisionTopPipe;
    private Rectangle collisionBottomPipe;
    private float x;
    private float y;
    private int bottomH;
    Random random = new Random();
    private  float speed = 3;
    private Sound flying;
    private boolean passed;


    public int getBottomH() {
        return bottomH - GROUND;
    }
    public void setBottomH(int bottomH) {
        this.bottomH = bottomH;
    }
    public int getGap() {
        return GAP;
    }
    public static int getGround() {
        return GROUND;
    }
    public Rectangle getBotomPipe() {
        return collisionBottomPipe;
    }
    public Rectangle getTopPipe() {
        return collisionTopPipe;
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static float getPipeWidth() {
        return PIPE_WIDTH;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Pipe(){
            bottomH = generateHeight();
            collisionBottomPipe = new Rectangle(Gdx.graphics.getWidth() * 2, GROUND , PIPE_WIDTH, bottomH - GROUND);
            collisionTopPipe = new Rectangle(Gdx.graphics.getWidth() * 2, bottomH + SPACE + GROUND, PIPE_WIDTH, (Gdx.graphics.getHeight() - (SPACE + bottomH)));
//            System.out.println(collisionBottomPipe.getX()+ " "+ collisionBottomPipe.getY());
            passed = false;
    }

    public  int generateHeight()
    {
        int min = 0;
        int max = Gdx.graphics.getHeight() / 800 * 200;
        int i = random.nextInt(max-min + 1);
        return i + GROUND + GAP;
    }


    public void setX(float x) {
        this.x = x;
        collisionTopPipe.setX(x - (PIPE_WIDTH / 2));
        collisionBottomPipe.setX(x - PIPE_WIDTH / 2);
    }


    public boolean isPassed() {
        return passed;
    }

    public void update() {
        setX(x - speed);
    }


}

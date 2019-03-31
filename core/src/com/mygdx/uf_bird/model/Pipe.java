package com.mygdx.uf_bird.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Pipe implements Object{


    private static final int PIPE_WIDTH = Gdx.graphics.getWidth() / 400 * 54 * 2;
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;
    private static final int SPACE = Gdx.graphics.getHeight() / 800 * 100;
    private static final int GAP = Gdx.graphics.getHeight() / 800 * 200;


    private Rectangle collisionTopPipe;
    private Rectangle collisionBottomPipe;
    private float x;
    private float y;

    public int getBottomH() {
        return bottomH - GROUND;
    }

    public void setBottomH(int bottomH) {
        this.bottomH = bottomH;
    }
    public void getG(int bottomH) {
        this.bottomH = bottomH;
    }

    public static int getGround() {
        return GROUND;
    }

    private int bottomH;
    Random random = new Random();
    private  float speed = 3;

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




    public Pipe(){
            bottomH = generateHeight();
            collisionBottomPipe = new Rectangle(Gdx.graphics.getWidth() * 2, GROUND , PIPE_WIDTH, bottomH - GROUND);
            collisionTopPipe = new Rectangle(Gdx.graphics.getWidth() * 2, bottomH + SPACE + GROUND, PIPE_WIDTH, (Gdx.graphics.getHeight() - (SPACE + bottomH)));
            System.out.println(collisionBottomPipe.getX()+ " "+ collisionBottomPipe.getY());
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


    public void update() {
        setX(x - speed);
    }
//
//    public void draw(){}

}

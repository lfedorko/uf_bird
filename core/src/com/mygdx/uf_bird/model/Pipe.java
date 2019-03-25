package com.mygdx.uf_bird.model;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.uf_bird.GameMain;

import java.util.Random;

public class Pipe implements Object{

    private static final int PIPE_WIDTH = 52;
    private static final int GROUND = 100;
    private static final int SPACE = 100;


    private Rectangle collisionTopPipe;
    private Rectangle collisionBottomPipe;
    private float x;
    private float y;
    private int bottomH;

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
            collisionBottomPipe = new Rectangle(800, GROUND , PIPE_WIDTH, bottomH - GROUND);
            collisionTopPipe = new Rectangle(800, bottomH + SPACE + GROUND, PIPE_WIDTH, (GameMain.H - (SPACE + GROUND + bottomH)));
            System.out.println(collisionBottomPipe.getX()+ " "+ collisionBottomPipe.getY());
    }

    public static int generateHeight()
    {
        int min = 0;
        int max = 300;
        Random random = new Random();
        int i = random.nextInt(max-min + 1);
        i += min;
        return i + GROUND + 100;
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

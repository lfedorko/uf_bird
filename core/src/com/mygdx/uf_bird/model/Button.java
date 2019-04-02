package com.mygdx.uf_bird.model;

import com.badlogic.gdx.graphics.Texture;

import java.awt.TextArea;

public class Button {

    private Texture btn;
    private int x;
    private int y;
    private int h;
    private int w;


    public Button(int x, int y, int w, int h, String name){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        btn = new Texture(name);
    }

    public boolean isClicked(int outX, int outY){
        if (outX < x + w &&  outX > x &&  outY < y + h && outY > y)
            return true;
        return false;
    }

    public Texture getBtn() {
        return btn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }


}

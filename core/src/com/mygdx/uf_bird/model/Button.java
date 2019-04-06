package com.mygdx.uf_bird.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.uf_bird.view.GameView;

public class Button implements ObjectToDraw{

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


    @Override
    public void draw() {
        GameView.batch.draw(btn,x,y,w,h);
    }

    @Override
    public void dispose(){

        btn.dispose();
    }
}

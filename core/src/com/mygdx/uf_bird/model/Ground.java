package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import static sun.misc.Version.print;

public class Ground implements Object {
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 110;
    private static final int WIDTH_GROUND  = 24;
    private float firstPos;
    private float secondPos;

    public float getFirstPos() {
        return firstPos;
    }

    public void setFirstPos(float firstPos) {
        this.firstPos = firstPos;
    }

    public float getSecondPos() {
        return secondPos;
    }

    public void setSecondPos(float secondPos) {
        this.secondPos = secondPos;
    }

    public Ground(){
        firstPos = 0;
        secondPos = Gdx.graphics.getWidth();
    }



    public void update() {
        firstPos -= 4;
        secondPos -= 4;
        System.out.println(firstPos + " " + secondPos);
        if (firstPos == -Gdx.graphics.getWidth())
            firstPos = Gdx.graphics.getWidth();
        else if (secondPos == -Gdx.graphics.getWidth())
            secondPos = Gdx.graphics.getWidth();

    }
//
        public void draw() {

        }

}

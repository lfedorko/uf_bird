package com.mygdx.uf_bird.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.uf_bird.GameMain;
import com.mygdx.uf_bird.view.MainScreen;

import java.awt.*;

public class PipeCollector {

    private static final int AMOUNT_OF_PIPES = 4;
    private static final float BEETWEEN_PAIR = 200;
    private TextureAtlas texture;

    private Sound passed;
    private Array<Pipe> pipes = new Array<Pipe>();

    public PipeCollector(){
        passed = Gdx.audio.newSound(Gdx.files.internal("point.ogg"));
        for (int i = 0; i < AMOUNT_OF_PIPES; i++) {
            Pipe pipe = new Pipe();
            pipe.setX(GameMain.W + i * BEETWEEN_PAIR + 100);
            pipes.add(pipe);
        }
    }

    public Array<Pipe> getPipes() {
        return pipes;
    }

    public void update(){
        Pipe firstPipe = pipes.first();
        if (firstPipe.getX() < GameMain.W / 200)
            passed.play();
        if (firstPipe.getX() < (-firstPipe.getPipeWidth()) / 2) {
            pipes.removeValue(firstPipe, true);
            Pipe lastPipe = pipes.get(pipes.size - 1);
            Pipe newPipe = new Pipe();
            newPipe.setX(lastPipe.getX() + BEETWEEN_PAIR);
            pipes.add(newPipe);
        }
        for (Pipe pipe : pipes)
            pipe.update();

    }

   public boolean сheckOverlap(Rectangle rect){
        for (Pipe pipe: pipes)
        {
            if (rect.overlaps(pipe.getBotomPipe()) || (rect.overlaps(pipe.getTopPipe())))
               return true;
        }
        return false;
    }
}


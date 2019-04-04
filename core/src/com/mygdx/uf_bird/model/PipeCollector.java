package com.mygdx.uf_bird.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


import java.awt.*;

public class PipeCollector {

    private static final int AMOUNT_OF_PIPES = 4;
    private static final float BEETWEEN_PAIR = Gdx.graphics.getWidth() / 2f;
    private TextureAtlas texture;

    private Sound passed;
    private Array<Pipe> pipes = new Array<Pipe>();
    private int score;

    public PipeCollector(){
        passed = Gdx.audio.newSound(Gdx.files.internal("music/point.ogg"));
        for (int i = 0; i < AMOUNT_OF_PIPES; i++) {
            Pipe pipe = new Pipe();
            pipe.setX(Gdx.graphics.getWidth() + i * BEETWEEN_PAIR);
            pipes.add(pipe);
        }
        score = 0;
    }

    public Array<Pipe> getPipes() {
        return pipes;
    }

    public void update(){
        Pipe firstPipe = pipes.first();
        if (firstPipe.getX() == Gdx.graphics.getWidth() / 2) {
            passed.play();
            setScore();
        }
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

    public int getScore() {
        return score;
    }

    public void setScore()
    {
        score +=1;
        System.out.println(score);
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


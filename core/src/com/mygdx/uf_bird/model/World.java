package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class World {
    private Bird bird;
    private Ground ground;
    private PipeCollector pipes;
    private Sound coin;
    private Sound die;
    private Sound flap;

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    private Button startButton;

    public World(){
        bird = new Bird();
        ground = new Ground();
        pipes = new PipeCollector();
        startButton = new Button(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/4,"start2.png");
        die = Gdx.audio.newSound(Gdx.files.internal("music/die.ogg"));
        flap = Gdx.audio.newSound(Gdx.files.internal("music/wing.ogg"));
        coin = Gdx.audio.newSound(Gdx.files.internal("music/point.ogg"));
        state = "START";
    }

    public Button getStartButton() {
        return startButton;
    }

    public Bird getBird() {
        return bird;
    }

    public Ground getGround() {
        return ground;
    }

    public PipeCollector getPipes() {
        return pipes;
    }

    public String getState() {
        return state;
    }

    public void update(){
        if (state == "GAME") {
            if (!bird.isDead()) {
                pipes.update();
                bird.update();
                ground.update();
                if (bird.isJumped()) {
                    bird.jump();
                    flap.play();
                }if (pipes.сheckOverlap(bird.getColissionRectangle()) || pipes.checkMinTube(bird.getColissionRectangle())) {
                    die.play();
                    bird.setDead(true);
                }
                pipes.сheckPoint(bird);
            } else {
                bird.update();
            }
        }
        else
            ground.update();
    }



}

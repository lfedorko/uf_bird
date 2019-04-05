package com.mygdx.uf_bird.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.uf_bird.model.Bird;
import com.mygdx.uf_bird.model.Ground;
import com.mygdx.uf_bird.model.PipeCollector;
import com.mygdx.uf_bird.model.World;
import com.mygdx.uf_bird.view.GameView;

public class GameController {

    private World world;
    private boolean isClicked;

    public GameController(World world){
       this.world = world;
    }


    public boolean touchDown(int x, int y) {
        if (world.getState() == "START"){
            if (world.getStartButton().isClicked(Gdx.graphics.getWidth() - x,Gdx.graphics.getHeight() - y))
                isClicked = true;
        }
        else if (world.getState() == "GAME")
        {
            world.getBird().setJumped(true);
        }
        return true;
    }

    public boolean touchUp() {
        if(isClicked){
            if (world.getState() == "START")
                world.setState("GAME");
                isClicked = false;
        }
        return true;

    }

    public void update()
    {
        world.update();
    }

}

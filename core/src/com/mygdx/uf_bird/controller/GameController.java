package com.mygdx.uf_bird.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.uf_bird.model.Bird;
import com.mygdx.uf_bird.model.Ground;
import com.mygdx.uf_bird.model.PipeCollector;
import com.mygdx.uf_bird.model.World;
import com.mygdx.uf_bird.view.GameView;

public final class GameController {

    private World world;
    private boolean isClicked;
    private static GameController instance;

    public static GameController getInstance(World world) {
        if (instance == null) {
            instance = new GameController(world);
        }
        return instance;
    }

    private GameController(World world){
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
        else if (world.getState() == "OVER")
        {
            if (world.getRestartButton().isClicked(x,Gdx.graphics.getHeight() - y))
                isClicked = true;
        }
        return true;
    }

    public boolean touchUp() {
        if(isClicked){
            if (world.getState() == "START") {
                world.setState("GAME");
                isClicked = false;
            }
            else if (world.getState() == "OVER")
            {
                world.setState("START");
                world.reinit();
                isClicked = false;
            }
        }
        return true;

    }

    public void update()
    {
        world.update();
    }

}

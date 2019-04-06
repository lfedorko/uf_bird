package com.mygdx.uf_bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.uf_bird.controller.Launch;

public class MyGame extends Game {
    @Override
    public void create() {
        this.setScreen(new Launch());

    }


}

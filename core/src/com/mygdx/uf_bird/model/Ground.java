package com.mygdx.uf_bird.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.uf_bird.view.GameView;


public class Ground implements ObjectToDraw {
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;
    private TextureAtlas atlas;
    private static final int WIDTH_GROUND  = 24;
    private float firstPos;
    private float secondPos;
    private TextureRegion ground;
    private TextureRegion bg;


    public Ground(){
        firstPos = 0;
        secondPos = Gdx.graphics.getWidth();
        atlas = new TextureAtlas(Gdx.files.internal("test.atlas"));
        ground = atlas.findRegion("ground");
        bg = atlas.findRegion("bg");

    }

    public void update() {
        firstPos -= 1;
        secondPos -= 1;
        if (firstPos == 0)
            secondPos = Gdx.graphics.getWidth();
        else if (secondPos == 0)
            firstPos = Gdx.graphics.getWidth();

    }

    public void draw() {
            GameView.batch.draw(bg, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            GameView.batch.draw(ground, firstPos,0, Gdx.graphics.getWidth(), GROUND);
            GameView.batch.draw(ground, secondPos,0, Gdx.graphics.getWidth(), GROUND);

        }

}

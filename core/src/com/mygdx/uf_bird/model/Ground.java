package com.mygdx.uf_bird.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.uf_bird.view.GameView;


public class Ground implements ObjectToDraw {
    private static final int WIDTH = 168;
    private static final int GROUND = Gdx.graphics.getHeight() / 800 * 100;
    private int amount = Gdx.graphics.getWidth()/168 + 2;
    private int[] intArray = new int[amount];
    private TextureAtlas atlas;
    private int firstPos;
    private float secondPos;
    private TextureRegion ground;
    private TextureRegion bg;
    private boolean stop;


    public Ground(){
        firstPos = -WIDTH/2;
        secondPos = Gdx.graphics.getWidth() - 2;
        atlas = new TextureAtlas(Gdx.files.internal("test.atlas"));
        ground = atlas.findRegion("ground");
        bg = atlas.findRegion("bg");

        for (int i = 0; i < amount; i++) {
            intArray[i] = firstPos;
            firstPos += WIDTH;
        }
        stop = false;
    }

    public void reset(){stop = false; }

    public void stop(){
        stop = true;
    }

    public void update() {
        int delta = 2;
        if (!stop){
            for (int i = 0; i < amount; i++) {
                if (intArray[i] <= -WIDTH) {
                    System.out.println("Help"+ intArray[i]);
                    intArray[i] = WIDTH * (amount - 1);
                }
                    intArray[i] -= delta;
                firstPos += WIDTH;
            }
        }
    }

    public void draw() {
            GameView.batch.draw(bg, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            for (int i = 0; i < amount; i++) {
                GameView.batch.draw(ground, intArray[i], 0, WIDTH, GROUND);
            }
        }

        public void dispose(){
        atlas.dispose();
        }
}

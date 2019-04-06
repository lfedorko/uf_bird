package com.mygdx.uf_bird.model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.uf_bird.view.GameView;

public class PipeCollector  implements ObjectToDraw{

    private static final int AMOUNT_OF_PIPES = 4;
    private static final float BEETWEEN_PAIR = Gdx.graphics.getWidth() / 2;


    private TextureAtlas texture;
    private Array<Pipe> pipes = new Array<Pipe>();
    private int score;
    private  TextureRegion base;
    private  TextureRegion topPipeS;
    private Sound coin;

    public PipeCollector(){
        texture = new TextureAtlas(Gdx.files.internal("flappy.txt"));
        topPipeS = texture.findRegion("cap");
        base = texture.findRegion("base_pipe");
        coin = Gdx.audio.newSound(Gdx.files.internal("music/point.ogg"));
        for (int i = 0; i < AMOUNT_OF_PIPES; i++) {
            Pipe pipe = new Pipe();
            pipe.setX(Gdx.graphics.getWidth() + i * BEETWEEN_PAIR);
            pipes.add(pipe);
        }
        score = 0;
    }

    public void reset(){
        for (int i = 0; i < AMOUNT_OF_PIPES; i++) {
            pipes.pop();
        }
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

    @Override
    public void draw() {
        for (Pipe pipe: pipes)
        {
            Rectangle botomPipe = pipe.getBotomPipe();
            Rectangle topPipe = pipe.getTopPipe();

            GameView.batch.draw(base, topPipe.x, topPipe.getY(), topPipe.getWidth(), topPipe.getHeight());
            GameView.batch.draw(topPipeS, topPipe.x, topPipe.getY(), topPipe.getWidth(), 25);
            GameView.batch.draw(base, botomPipe.x, botomPipe.y, botomPipe.width, botomPipe.height);
            GameView.batch.draw(topPipeS, botomPipe.x, botomPipe.y + botomPipe.height - 24, botomPipe.width, 25);
        }
    }

    public void update(){
        Pipe firstPipe = pipes.first();
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
            if (rect.overlaps(pipe.getBotomPipe()) || (rect.overlaps(pipe.getTopPipe())) || rect.y >= Gdx.graphics.getHeight())
            {
                return true;
            }
        }
        return false;
    }

    public void сheckPoint(Bird bird){
		for (Pipe pipe: pipes) {
		    if ((pipe.getX() + pipe.getPipeWidth() / 2 < bird.getX()) && !pipe.isPassed()) {
		        System.out.println(pipe.getX() + pipe.getPipeWidth() / 2  +" "+ bird.getX());
                pipe.setPassed(true);
                coin.play();
                score++;
            }
        }
	}

    public void dispose() {
        texture.dispose();
        coin.dispose();
    }


}


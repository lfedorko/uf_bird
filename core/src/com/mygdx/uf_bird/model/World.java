package com.mygdx.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import static java.awt.image.ImageObserver.HEIGHT;
import static jdk.nashorn.internal.objects.Global.print;

public class World  implements ObjectToDraw{
    private Bird bird;
    private Ground ground;
    private PipeCollector pipes;
    private Sound die;
    private Sound flap;
    private int maxScore;
    private int userScore;

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    private Button startButton;

    public Button getRestartButton() {
        return restartButton;
    }

    private Button restartButton;

    public World() {
        bird = new Bird();
        ground = new Ground();
        pipes = new PipeCollector();
        startButton = new Button(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 4, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 4, "start2.png");
        restartButton = new Button(Gdx.graphics.getWidth() / 2 -Gdx.graphics.getWidth() / 18, Gdx.graphics.getHeight() / 6 * 3 , Gdx.graphics.getWidth() / 9, Gdx.graphics.getHeight() / 12, "start2.png");
        die = Gdx.audio.newSound(Gdx.files.internal("music/die.ogg"));
        flap = Gdx.audio.newSound(Gdx.files.internal("music/wing.ogg"));
        state = "START";
    }

    public void reinit(){
        bird.reset();
        pipes.reset();
        ground.reset();
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

    public int getmaxScore() {
        return maxScore;
    }

    public int getuserScore() {
        return userScore;
    }

    public int getMedal(){
        int number = 3;
        if (maxScore > 5)
            number = 2;
        else if (maxScore > 10)
            number = 1;
        else if (maxScore > 50)
            number = 0;
        return number;
    }

    public void update() {

        if (state == "GAME") {
            if (!bird.isDead()) {
                pipes.update();
                ground.update();
                if (pipes.сheckOverlap(bird.getColissionRectangle() )){
                    die.play();
                    bird.setY(bird.getColissionRectangle().y += 2 * HEIGHT);
                    bird.setDead(true);
                }
                else
                    bird.update();
                if (bird.isJumped()) {
                    bird.jump();
                    flap.play();
                }
                pipes.сheckPoint(bird);
            } else {

                bird.update();
                if (bird.isOnGround()) {
                    state = "OVER";
                    ground.stop();
                    userScore = pipes.getScore();
                    if (maxScore < userScore)
                        maxScore = userScore;
                }
            }
        } else if (state == "START")
            ground.update();
        else if (state == "OVER") {
            ground.stop();
            ground.update();
            bird.update();
        }
    }

    @Override
    public void draw() {

    }

    public void dispose(){
        pipes.dispose();
        bird.dispose();
        ground.dispose();
        startButton.dispose();
        restartButton.dispose();
        die.dispose();
        flap.dispose();
    }


}

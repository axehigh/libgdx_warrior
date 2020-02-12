package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;


    private Hero hero;
    private BackgroundManager background;
    private int screenWidth;

    private List<Obstacle> obstacleList = new ArrayList<>();
    private int obstacleCounter = 10;

    @Override
    public void create() {
        batch = new SpriteBatch();

        hero = new Hero();

        screenWidth = Gdx.graphics.getWidth();
        background = new BackgroundManager(screenWidth);
    }

    @Override
    public void render() {
        background.update();
        hero.update();

        obstacleCounter--;

        if (obstacleCounter < 0) {
            Random random = new Random();
            obstacleCounter = random.nextInt(200) + 200;
            Gdx.app.log("MyTag", "Created obstacle" + obstacleCounter);
            obstacleList.add(new Obstacle(screenWidth, 150));
        }


        for (Obstacle obstacle : obstacleList) {
            obstacle.update();
        }

//        obstacleList = obstacleList.stream().filter(x -> x.isAlive()).collect(Collectors.toCollection());

        batch.begin();
        background.render(batch);
        hero.render(batch);
        for (Obstacle obstacle : obstacleList) {
            obstacle.render(batch);
        }
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}

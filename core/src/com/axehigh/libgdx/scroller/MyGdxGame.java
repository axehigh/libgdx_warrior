package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kotlincode.Enemy;


public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;

    BitmapFont font;


    private Hero hero;
    private BackgroundManager background;
    private int screenWidth;

    private List<Obstacle> obstacleList = new ArrayList<>();
    private int obstacleCounter = 10;

    private Enemy enemy;

    @Override
    public void create() {
        batch = new SpriteBatch();

        hero = new Hero(this);

        screenWidth = Gdx.graphics.getWidth();
        background = new BackgroundManager(screenWidth);
        font = new BitmapFont();
        font.getData().setScale(4f);

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
            obstacleList.add(new Obstacle(this, screenWidth, 150));
        }


        for (Obstacle obstacle : obstacleList) {
            obstacle.update();
            if (obstacle.rect.collision(hero.rect)) {
                Gdx.app.log("MyTag", "Collision true");
                hero.hit(1);

            }
        }


//        obstacleList = obstacleList.stream().filter(x -> x.isAlive()).collect(Collectors.toCollection());

        batch.begin();
        background.render(batch);
        if (hero.isAlive()) {
            hero.render(batch);
        }

        //font.draw(batch, "Hello World!" + hero.getLife(), 200, 200);
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

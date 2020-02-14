package com.axehigh.libgdx.scroller.screen;

import com.axehigh.libgdx.scroller.background.BackgroundManager;
import com.axehigh.libgdx.scroller.hero.Hero;
import com.axehigh.libgdx.scroller.MyGdxGame;
import com.axehigh.libgdx.scroller.obstacle.Obstacle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen extends ScreenAdapter {
    private final BackgroundManager background;
    private final int screenWidth;
    MyGdxGame game;

    private Hero hero;

    private List<Obstacle> obstacleList = new ArrayList<>();
    private int obstacleCounter = 10;


    public GameScreen(MyGdxGame game) {
        this.game = game;
        hero = new Hero(game);

        background = game.getBackgroundManager();
        screenWidth = game.getScreenWidth();

    }

    @Override
    public void render(float delta) {
        background.update();
        hero.update();


        obstacleCounter--;

        if (obstacleCounter < 0) {
            Random random = new Random();
            obstacleCounter = random.nextInt(200) + 200;
            Gdx.app.log("MyTag", "Created obstacle" + obstacleCounter);
            obstacleList.add(new Obstacle(game, screenWidth, 150));
        }


        for (Obstacle obstacle : obstacleList) {
            obstacle.update();
            if (obstacle.rect.collision(hero.rect)) {
                Gdx.app.log("MyTag", "Collision true");
                hero.hit(1);

            }
        }


//        obstacleList = obstacleList.stream().filter(x -> x.isAlive()).collect(Collectors.toCollection());

        game.batch.begin();
        background.render(game.batch);
        if (hero.isAlive()) {
            hero.render(game.batch);
        } else {
            game.setScreen(new EndScreen(game));
        }

        //font.draw(batch, "Hello World!" + hero.getLife(), 200, 200);
        for (Obstacle obstacle : obstacleList) {
            obstacle.render(game.batch);
        }
        game.batch.end();
    }

}

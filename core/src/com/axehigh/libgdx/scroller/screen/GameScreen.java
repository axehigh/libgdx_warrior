package com.axehigh.libgdx.scroller.screen;

import com.axehigh.libgdx.scroller.GameText;
import com.axehigh.libgdx.scroller.MyGdxGame;
import com.axehigh.libgdx.scroller.background.BackgroundManager;
import com.axehigh.libgdx.scroller.hero.Hero;
import com.axehigh.libgdx.scroller.obstacle.Obstacle;
import com.axehigh.libgdx.scroller.utils.GfxUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameScreen extends ScreenAdapter {
    private final BackgroundManager background;
    private final int screenWidth;
    MyGdxGame game;

    private Hero hero;

    private List<Obstacle> obstacleList = new ArrayList<>();
    private int obstacleCounter = 10;

    private long gameCounter = 0;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        hero = new Hero(game);
        game.gameScore.resetGame();

        background = game.getBackgroundManager();
        screenWidth = game.getScreenWidth();

    }

    @Override
    public void render(float delta) {
        background.update();
        hero.update();


        obstacleCounter--;
        gameCounter++;

        generateObstacles();
        checkCollisionWithObstacles();
        removeObstacles(obstacleList);


        GfxUtils.clearScreen();

        game.batch.begin();
        background.render(game.batch);
        if (hero.isAlive()) {
            hero.render(game.batch);
        } else {
            boolean newHiscore = game.gameScore.checkHiscore();
            game.setScreen(new EndScreen(game,newHiscore));
        }

        for (Obstacle obstacle : obstacleList) {
            obstacle.render(game.batch);
        }

        game.gameScore.render();
        game.batch.end();

        if (game.debugRect) {
            GfxUtils.drawRectangle(game.shape, hero.getRect());
            for (Obstacle obstacle : obstacleList) {
                GfxUtils.drawRectangle(game.shape, obstacle.rect);
            }
        }
    }

    private void generateObstacles() {
        if (obstacleCounter < 0) {
            Random random = new Random();
            obstacleCounter = random.nextInt(100) + 75;
            Gdx.app.log(GameText.gameTag, "Created obstacle" + obstacleCounter);
            int speed = Math.max(game.gameScore.getScore() * 5, 100);
            obstacleList.add(new Obstacle(game, screenWidth, 200 + speed));
        }
    }

    private void checkCollisionWithObstacles() {
        for (Obstacle obstacle : obstacleList) {
            obstacle.update();
            if (obstacle.rect.overlaps(hero.getRect())) {
                Gdx.app.log(GameText.gameTag, "Collision true");
                hero.hit(1);
            }
        }
    }

    private void removeObstacles(List<Obstacle> obstacleList) {
        Iterator<Obstacle> iterator = obstacleList.iterator();
        while (iterator.hasNext()) {
            Obstacle next = iterator.next();
            if (!next.isAlive()) {
                iterator.remove();
                int score = game.gameScore.addScore(1);
                Gdx.app.log(GameText.gameTag, "Ninja score " + score);
            }
        }
//        Gdx.app.log(GameText.gameTag, "Ninjas alive" + obstacleList.size());
    }

}

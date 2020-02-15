package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.Gdx;

import lombok.Getter;

@Getter
public class GameScore {

    private final MyGdxGame game;
    private int score = 0;
    private int hiScore = 0;

    public GameScore(MyGdxGame game) {
        this.score = 0;
        this.hiScore = 0;
        this.game = game;
    }


    public int addScore(int value) {
        score = score + value;
        return score;
    }

    public void resetGame() {
        score = 0;
    }

    public void render() {
        game.font.draw(game.batch, GameText.scoreText + score, 50, Gdx.graphics.getHeight() - 50);
    }

    public void renderHiscore() {
        game.font.draw(game.batch, GameText.hiScoreText + hiScore, 50, Gdx.graphics.getHeight() - 50);
    }

    public boolean checkHiscore() {
        if (score > hiScore) {
            hiScore = score;
        }

        return hiScore == score && score != 0;
    }
}

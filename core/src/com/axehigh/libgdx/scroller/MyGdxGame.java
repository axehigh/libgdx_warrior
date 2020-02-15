package com.axehigh.libgdx.scroller;

import com.axehigh.libgdx.scroller.background.BackgroundManager;
import com.axehigh.libgdx.scroller.screen.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MyGdxGame extends Game {
    public boolean debug = false;
    public boolean debugCoord =false;
    public boolean debugRect =false;

    public SpriteBatch batch;
    public BitmapFont font;
    public ShapeRenderer shape;


    public GameScore gameScore;

    BackgroundManager backgroundManager;
    int screenWidth;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4f);
        font.setColor(Color.BLACK);
        setScreen(new MenuScreen(this));
        screenWidth = Gdx.graphics.getWidth();
        backgroundManager = new BackgroundManager(screenWidth);
        gameScore = new GameScore(this);
        shape = new ShapeRenderer();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public BackgroundManager getBackgroundManager() {
        return backgroundManager;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}

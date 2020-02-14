package com.axehigh.libgdx.scroller;

import com.axehigh.libgdx.scroller.background.BackgroundManager;
import com.axehigh.libgdx.scroller.screen.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public boolean debug = false;

    BackgroundManager backgroundManager;
    int screenWidth;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4f);
        setScreen(new MenuScreen(this));
        screenWidth = Gdx.graphics.getWidth();
        backgroundManager = new BackgroundManager(screenWidth);
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

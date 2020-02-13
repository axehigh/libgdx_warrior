package com.axehigh.libgdx.scroller;

import com.axehigh.libgdx.scroller.screen.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4f);
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

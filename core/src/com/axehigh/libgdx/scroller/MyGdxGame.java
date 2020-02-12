package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;

    private Hero hero;
    private BackgroundManager background;
    private int screenWidth;

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

        batch.begin();
        background.render(batch);
        hero.render(batch);
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}

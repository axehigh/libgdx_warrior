package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacle {
    private float x;
    private float y;

    private boolean alive = true;

    private Texture image;
    private float velocity;

    public Obstacle(int startingX, int velocity) {
        this.image = new Texture("obstacle/ninja.png");
        this.y = 102;
        this.x = startingX;
        this.velocity = velocity;
    }


    public void update() {
        float dt = Gdx.graphics.getDeltaTime();

        x = (x - (velocity * dt));


        if (x < 0) {
            alive = false;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, x, y);
    }

    public boolean isAlive() {
        return alive;
    }
}

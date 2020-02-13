package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obstacle {
    private final MyGdxGame game;
    private float x;
    private float y;

    private boolean alive = true;

    private Texture image;
    private float velocity;

    CollisionRectangle rect;

    public Obstacle(MyGdxGame myGdxGame, int startingX, int velocity) {
        this.game = myGdxGame;
        this.image = new Texture("obstacle/ninja.png");
        this.y = 102;
        this.x = startingX;
        this.velocity = velocity;
        rect = new CollisionRectangle((int) this.x, (int) this.y, this.image.getWidth(), this.image.getHeight());
    }


    public void update() {
        float dt = Gdx.graphics.getDeltaTime();

        x = (x - (velocity * dt));


        if (x < 0) {
            alive = false;
        }

        rect.move((int) this.x, (int) this.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, x, y);
        game.font.draw(batch, rect.toString(), this.x, this.y + image.getHeight());
    }

    public boolean isAlive() {
        return alive;
    }
}

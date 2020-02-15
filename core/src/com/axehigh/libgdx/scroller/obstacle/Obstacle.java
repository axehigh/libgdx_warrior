package com.axehigh.libgdx.scroller.obstacle;

import com.axehigh.libgdx.scroller.MyGdxGame;
import com.axehigh.libgdx.scroller.utils.CollisionRectangle;
import com.axehigh.libgdx.scroller.utils.GfxUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {
    private final MyGdxGame game;
    private float x;
    private float y;

    private boolean alive = true;

    private float velocity;

    public CollisionRectangle rect;
    private List<Texture> images;
    private int frame = 0;
    private int frameSpeed = 100;

    public Obstacle(MyGdxGame myGdxGame, int startingX, int velocity) {
        this.game = myGdxGame;
        images = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            images.add(new Texture("obstacle/demon1/Demon_01_Walking_00" + i + ".png"));
        }

        for (int i = 10; i <= 17; i++) {
            images.add(new Texture("obstacle/demon1/Demon_01_Walking_0" + i + ".png"));
        }

        this.y = 102;
        this.x = startingX;
        this.velocity = velocity;
        rect = new CollisionRectangle((int) this.x, (int) this.y, this.images.get(frame).getWidth(), this.images.get(frame).getHeight());
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
        frame = frame + (int) (Gdx.graphics.getDeltaTime() * frameSpeed);
        if (frame > 17) {
            frame = 0;
        }
        batch.draw(images.get(frame), x, y);
        if (game.debugCoord) {
            game.font.draw(batch, rect.toString(), this.x, this.y + images.get(frame).getHeight());
        }
    }

    public boolean isAlive() {
        return alive;
    }
}

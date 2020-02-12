package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;


public class BackgroundElement {
    float x, y;
    int speed;
    Texture image;

    public BackgroundElement(GridPoint2 xy, int speed, Texture image) {
        this.x = xy.x;
        this.y = xy.y;
        this.speed = speed;
        this.image = image;
    }

    public void update() {
        this.x = (this.x - Gdx.graphics.getDeltaTime() * speed) % image.getWidth();
//        Gdx.app.log("MyTag", "my informative message" + this.xy.x);
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, x, y);
        batch.draw(image, x + image.getWidth(), y);
    }

}

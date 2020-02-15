package com.axehigh.libgdx.scroller.utils;

import com.axehigh.libgdx.scroller.GameText;
import com.badlogic.gdx.Gdx;

public class CollisionRectangle {

    public int x, y, width, height;

    public CollisionRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean collision(CollisionRectangle rect) {

        return this.x < rect.x + rect.width &&
                rect.x < this.x + this.width &&
                this.y < rect.y + rect.height &&
                rect.y < this.y + this.height;
    }

    public boolean collision(int _x, int _y) {
        Gdx.app.log(GameText.gameTag, "Rect: " + toString());
        Gdx.app.log(GameText.gameTag, "_x_y: " + _x + "," + _y);
        return (_x < this.x + this.width &&
                this.y > _y && _y < this.y + this.height);
    }

    public String toString() {
        return "rect:" + x + ", " + y + ", " + width + ", " + height;
    }
}

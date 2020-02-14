package com.axehigh.libgdx.scroller.utils;

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

    public String toString() {
        return "rect:" + x + ", " + y + ", " + width + ", " + height;
    }
}

package com.axehigh.libgdx.scroller.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import lombok.Getter;

@Getter
public class ImageRectangle {

    public final static String playButtonImage = "buttons/button_play.png";
    public final static String menuButtonImage = "buttons/button_menu.png";

    private final Texture image;
    private Rectangle rect;

    public ImageRectangle(String fileName, int x, int y) {
        this(fileName, x, y, true);
    }

    public ImageRectangle(String fileName, int x, int y, boolean center) {
        this.image = new Texture(fileName);
        int xRect = x;
        int yRect = y;
        if (center) {
            xRect = x - image.getWidth() / 2;
            yRect = y - image.getHeight() / 2;
        }

        rect = new Rectangle(xRect, yRect, image.getWidth(), image.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, rect.x, rect.y);
    }

    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(image, rect.x + x, rect.y + y);
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    public void drawRectangle(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.RED);
        shape.rect(rect.x, rect.y, rect.width, rect.height);
        shape.end();
    }
}

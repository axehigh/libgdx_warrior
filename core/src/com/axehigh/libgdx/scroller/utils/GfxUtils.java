package com.axehigh.libgdx.scroller.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GfxUtils {
    public static void clearScreen() {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static int getCenterX() {
        return (int) (Gdx.graphics.getWidth() * .50f);
    }

    public static int getCenterY() {
        return (int) (Gdx.graphics.getHeight() * .50f);
    }

    public float deltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public static void drawRectangle(ShapeRenderer shape, Rectangle rect) {
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.RED);
        shape.rect(rect.x, rect.y, rect.width, rect.height);
        shape.end();
    }
}

package com.axehigh.libgdx.scroller.background;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class BackgroundManager {
    private final BackgroundElement ground;
    private int screenWidth;
    private List<BackgroundElement> grounds = new ArrayList<>();


    public BackgroundManager(int screenWidth) {

        this.screenWidth = screenWidth;


        ground = new BackgroundElement(new GridPoint2(0, 0), 50, new Texture("background/ground.png"));

        grounds.add(new BackgroundElement(new GridPoint2(0, 0), 50, new Texture("background/1.png")));
        grounds.add(new BackgroundElement(new GridPoint2(0, 0), 8, new Texture("background/2.png")));
        grounds.add(new BackgroundElement(new GridPoint2(0, 0), 10, new Texture("background/3.png")));
        grounds.add(new BackgroundElement(new GridPoint2(0, 0), 12, new Texture("background/4.png")));
        grounds.add(new BackgroundElement(new GridPoint2(0, 0), 13, new Texture("background/5.png")));
    }


    public void update() {
        for (BackgroundElement backgroundElement : grounds) {
            backgroundElement.update();
        }
        ground.update();


    }

    public void render(SpriteBatch gfx) {
        for (BackgroundElement backgroundElement : grounds) {
            backgroundElement.render(gfx);
        }
        ground.render(gfx);
    }


}

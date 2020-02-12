package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
    private float x;
    private float y;

    private float yVelocity = 0;
    private int jumpHeight = 300;
    private int gravity = -500;
    private int ground = 102;
    private Texture image;

    public Hero() {
        image = new Texture("viking.png");
        y = ground;
        x = 50;
    }


    public void update() {
        keyboardInput();
        float dt = Gdx.graphics.getDeltaTime();

        if (yVelocity != 0) {
            y = (y + yVelocity * dt);
            yVelocity = yVelocity + gravity * dt;
        }

        if (y < ground) {
            yVelocity = 0;
            y = ground;
        }
    }


    private void keyboardInput() {
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
//            jump();
//        }

        if (Gdx.input.isTouched()) {
            jump();
        }
    }

    private void jump() {

        if (yVelocity == 0) {
            yVelocity = jumpHeight;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, x, y);
    }
}

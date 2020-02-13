package com.axehigh.libgdx.scroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Hero {
    private float x;
    private float y;

    private float yVelocity = 0;
    private int jumpHeight = 300;
    private int gravity = -500;
    private int ground = 102;
    Texture image;

    public CollisionRectangle rect;

    MyGdxGame game;

    private int life = 3;
    private boolean alive = true;

    public Hero(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        image = new Texture("viking.png");
        y = ground;
        x = 50;
        rect = new CollisionRectangle((int) x, (int) y, image.getWidth(), image.getHeight());
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

        rect.move((int) x, (int) y);
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

        game.font.draw(batch, rect.toString(), this.x, this.y + image.getHeight());
    }

    public void hit(int i) {
        life--;
        if (life <= 0) {
            this.alive = false;
        }
    }
}

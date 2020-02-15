package com.axehigh.libgdx.scroller.hero;

import com.axehigh.libgdx.scroller.MyGdxGame;
import com.axehigh.libgdx.scroller.utils.CollisionRectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

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
    private int jumpHeight = 400;
    private int gravity = -500;
    private int ground = 102;

    private List<Texture> images;
    private float frame = 0;
    private int frameSpeed = 40;

    public CollisionRectangle rect;

    MyGdxGame game;

    private int life = 3;
    private boolean alive = true;

    public Hero(MyGdxGame myGdxGame) {
        this.game = myGdxGame;

        loadImages();

        y = ground;
        x = 200;
        rect = new CollisionRectangle((int) x, (int) y, images.get((int)frame).getWidth(), images.get((int)frame).getHeight());
    }

    private void loadImages() {
        images = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            images.add(new Texture("hero/Walking_00" + i + ".png"));
        }
        for (int i = 10; i <= 17; i++) {
            images.add(new Texture("hero/Walking_0" + i + ".png"));
        }
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
        frame = frame + (Gdx.graphics.getDeltaTime() * frameSpeed);
        if ((int) frame > 17) {
            frame = 0;
        }

        batch.draw(images.get((int) frame), x, y);

        if (game.debug) {
            game.font.draw(batch, rect.toString(), this.x, this.y + images.get(0).getHeight());
        }
    }

    public void hit(int i) {
        life--;
        if (life <= 0) {
            this.alive = false;
        }
    }
}

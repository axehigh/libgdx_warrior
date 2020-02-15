package com.axehigh.libgdx.scroller.screen;

import com.axehigh.libgdx.scroller.GameText;
import com.axehigh.libgdx.scroller.MyGdxGame;
import com.axehigh.libgdx.scroller.utils.GfxUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import static com.axehigh.libgdx.scroller.screen.ImageRectangle.menuButtonImage;
import static com.axehigh.libgdx.scroller.screen.ImageRectangle.playButtonImage;
import static com.axehigh.libgdx.scroller.utils.GfxUtils.getCenterX;
import static com.axehigh.libgdx.scroller.utils.GfxUtils.getCenterY;


public class EndScreen extends ScreenAdapter {

    private MyGdxGame game;
    private ImageRectangle playButton;
    private ImageRectangle menuButton;
    private OrthographicCamera camera;
    private ImageRectangle txtMessage;

    public EndScreen(MyGdxGame game, boolean newHiscore) {
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);


        if (newHiscore) {
            txtMessage = new ImageRectangle("messages/header_win.png", GfxUtils.getCenterX(), Gdx.graphics.getHeight());
        } else {
            txtMessage = new ImageRectangle("messages/header_failed.png", GfxUtils.getCenterX(), Gdx.graphics.getHeight());
        }

        menuButton = new ImageRectangle(menuButtonImage, getCenterX(), getCenterY(), true);
        playButton = new ImageRectangle(playButtonImage, getCenterX(), getCenterY() + menuButton.getImage().getHeight() + 10, true);


    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Gdx.app.log(GameText.gameTag, "touchXY: " + screenX + ", " + screenY);
                Vector3 v = new Vector3(screenX, screenY, 0);
                camera.unproject(v);
                if (playButton.contains((int) v.x, (int) v.y)) {
                    game.setScreen(new GameScreen(game));
                }
                if (menuButton.contains((int) v.x, (int) v.y)) {
                    game.setScreen(new MenuScreen(game));
                }
                Gdx.app.log(GameText.gameTag, "unprojectxy: " + (int) v.x + ", " + (int) v.y);
                Gdx.app.log(GameText.gameTag, menuButton.getRect().toString());
                Gdx.app.log(GameText.gameTag, playButton.getRect().toString());
                return false;
            }

        });
    }

    @Override
    public void render(float delta) {
        GfxUtils.clearScreen();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.getBackgroundManager().update();
        game.batch.begin();
        game.getBackgroundManager().render(game.batch);

        menuButton.render(game.batch,0,50);
        playButton.render(game.batch,0,100);
        txtMessage.render(game.batch, 0, -txtMessage.getImage().getHeight() - 10);
        game.batch.end();

        if (game.debugRect) {
            menuButton.drawRectangle(game.shape);
            playButton.drawRectangle(game.shape);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}

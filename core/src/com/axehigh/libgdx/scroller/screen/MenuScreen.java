package com.axehigh.libgdx.scroller.screen;

import com.axehigh.libgdx.scroller.GameText;
import com.axehigh.libgdx.scroller.MyGdxGame;
import com.axehigh.libgdx.scroller.utils.GfxUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.axehigh.libgdx.scroller.screen.ImageButton.playButtonImage;
import static com.axehigh.libgdx.scroller.utils.GfxUtils.getCenterX;
import static com.axehigh.libgdx.scroller.utils.GfxUtils.getCenterY;

public class MenuScreen extends ScreenAdapter {


    MyGdxGame game;
    ImageButton playButton;
    private OrthographicCamera camera;
    private final Music music;

    public MenuScreen(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);

        playButton = new ImageButton(playButtonImage, getCenterX(), getCenterY());

        music = Gdx.audio.newMusic(Gdx.files.internal("music/ice_giants.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Gdx.app.log("scroller", "touchXY: " + screenX + ", " + screenY);

                if (playButton.contains(screenX, screenY)) {
                    game.setScreen(new GameScreen(game));
                }
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
        game.font.draw(game.batch, GameText.gameTitle, Gdx.graphics.getWidth() * .45f, Gdx.graphics.getHeight() * .75f);
        playButton.render(game.batch);
        game.batch.end();
        if (game.debug) {
            playButton.drawRectangle(game.shape);
        }

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}

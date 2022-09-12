package com.tsits.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tsits.gamescreen.GameScreen;
import com.tsits.thing.Tsits;

public class MainMenuScreen implements Screen {

    final Tsits game;
    Texture img;
    OrthographicCamera camera;

    public MainMenuScreen(final Tsits game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        img = new Texture("donkey.jpg");
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to...", 100, 450);
        game.font.draw(game.batch, "The Shotgun In The Stone!", 100, 400);
        game.batch.draw(img, 0 ,0);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

package com.tsits.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tsits.screenhandler.Tsits;

public class GameScreen implements Screen {

    final Tsits game;
    public Texture img;
    public OrthographicCamera camera;


    public GameScreen(final Tsits game) {
        this.game = game;
        img = new Texture("placeholder.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0.2f, 0, 1);

        camera.update();
        game.batch.begin();
        game.batch.draw(img, 0 ,0);
        game.batch.end();

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

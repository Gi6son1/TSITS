package com.tsits.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tsits.screenhandler.Tsits;

public class MainMenuScreen implements Screen {

    final Tsits game;
    public Texture img;
    public OrthographicCamera camera;

    public MainMenuScreen(final Tsits game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        img = new Texture("mainmenuscreen.jpg");
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.begin();
        game.batch.draw(img, 0 ,0);
        game.fontTitle.draw(game.batch, "The Shotgun In The Stone!", 120, 600);
        game.font.draw(game.batch, "Press 'W' to start.", 100, 400);
        game.font.draw(game.batch, "Press 'H' for help.", 100, 300);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(44)) { //Changes screen to loading when 'P' pressed
            game.changeScreen(2);
            dispose();
        }
        if (Gdx.input.isKeyJustPressed(51)) { //Changes screen to game when 'W' pressed
            game.changeScreen(1);
            dispose();
        }
        if (Gdx.input.isKeyJustPressed(36)) { //Changes screen to help when 'H' pressed
            game.changeScreen(3);
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

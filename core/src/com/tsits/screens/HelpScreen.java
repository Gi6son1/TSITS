package com.tsits.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tsits.screenhandler.Tsits;

public class HelpScreen implements Screen {

    final Tsits game;
    public Texture img;
    public OrthographicCamera camera;

    public HelpScreen(final Tsits game) {
        this.game = game;
        img = new Texture("helpscreen.jpg");
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
        game.font.draw(game.batch, "Fight through the hordes of zombies to try and survive as long as possible.", 100, 600);
        game.font.draw(game.batch, "WASD keys for directional movement. Double tap for a short dash but watch your stamina.", 100, 500);
        game.font.draw(game.batch, "Aim and shoot your shotgun with your left mouse key. Right mouse key to unleash your bayonet!.", 100, 400);
        game.font.draw(game.batch, "Now press escape to return to the menu when you're ready, soldier.", 100, 300);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(111)) { //Changes screen to menu when escape pressed
            game.changeScreen(0);
            dispose();
        }

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

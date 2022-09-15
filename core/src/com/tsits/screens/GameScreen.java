package com.tsits.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tsits.game.PlayerCharacter;
import com.tsits.screenhandler.Tsits;

public class GameScreen implements Screen {

    final Tsits game;
    //public Texture img;
    public OrthographicCamera camera;

    SpriteBatch batch;
    PlayerCharacter playerCharacter; //this holds the player character object


    public GameScreen(final Tsits game) {
        this.game = game;
        this.batch = game.batch;
        playerCharacter = new PlayerCharacter();
        //img = new Texture("placeholder.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 1, 1, 1); //set window colour to white

        playerCharacter.trackPlayerMovement(); //method that allows user to control player movement

        camera.update(); //camera updates every frame
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        playerCharacter.sprite.draw(batch);
        playerCharacter.sprite.setPosition((int)playerCharacter.xPos, (int)playerCharacter.yPos); //draws playerCharacter on screen depending on their movement);

        playerCharacter.weapon.sprite.draw(batch);
        playerCharacter.weapon.sprite.setPosition((int)playerCharacter.weapon.xPos, (int)playerCharacter.weapon.yPos);
        playerCharacter.weapon.sprite.setRotation(playerCharacter.weapon.rotation);
        batch.end();

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

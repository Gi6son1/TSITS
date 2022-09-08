package com.tsits.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	PlayerCharacter playerCharacter;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		playerCharacter = new PlayerCharacter();
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);

		playerCharacter.trackPlayerMovement();

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerCharacter.sprite, (int)playerCharacter.xPos, (int)playerCharacter.yPos);
		batch.end();


	}



	@Override
	public void dispose () {
		batch.dispose();
	}
}

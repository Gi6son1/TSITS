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

	private int xMovement = 0;
	private int yMovement = 0;
	
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

		checkPlayerMovement();

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerCharacter.sprite, (int)playerCharacter.xPos, (int)playerCharacter.yPos);
		batch.end();


	}

	private void checkPlayerMovement(){
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			playerCharacter.xPos -= playerCharacter.getBaseMoveSpeed() * Gdx.graphics.getDeltaTime();
			if (playerCharacter.isFacingRight()){
				playerCharacter.flipSkinX();
			}
			xMovement = -1;
		}
		else {xMovement = 0;}

		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			playerCharacter.xPos += playerCharacter.getBaseMoveSpeed() * Gdx.graphics.getDeltaTime();
			if (!playerCharacter.isFacingRight()){
				playerCharacter.flipSkinX();
			}
			xMovement = 1;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			playerCharacter.yPos += playerCharacter.getBaseMoveSpeed() * Gdx.graphics.getDeltaTime();
			yMovement = 1;
		}
		else {yMovement = 0;}

		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			playerCharacter.yPos -= playerCharacter.getBaseMoveSpeed() * Gdx.graphics.getDeltaTime();
			yMovement = -1;
		}

		else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && playerCharacter.canDodge()){
			playerCharacter.isDodging = true;
		}

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

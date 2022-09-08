package com.tsits.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerCharacter {
    private Texture skin;
    Sprite sprite;
    float xPos;
    float yPos;
    int xMovement;
    int yMovement;

    private Weapon shotgun;
    private Weapon bayonet;
    private float stamina;
    private float maxStamina;
    private float baseMoveSpeed;
    private boolean facingRight;

    boolean isDodging;
    private float dodgeDistance;

    private float dodgeOriginX;
    private float dodgeOriginY;

    private float xDistance;
    private float yDistance;
    private float totalDistance;


    public PlayerCharacter(){
        this.skin = new Texture("manny.png");
        this.sprite = new Sprite(skin);
        this.xPos = 600;
        this.yPos = 310;
        this.baseMoveSpeed = 150.0f;
        this.facingRight = false;
        this.maxStamina = 100f;
        this.stamina = maxStamina;
        this.dodgeDistance = 75f;
    }

    private float getMoveSpeed() {
        float tempMoveSpeed = baseMoveSpeed;
        if (isDodging){
            return tempMoveSpeed * 6f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && stamina <= 0f){
            stamina = 0f;
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && stamina > 0f){
                stamina -= 30f * Gdx.graphics.getDeltaTime();
                tempMoveSpeed = baseMoveSpeed * 2f;
            }
            else {
                if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                    tempMoveSpeed = baseMoveSpeed * 0.5f;
                }
                stamina += 25f * Gdx.graphics.getDeltaTime();
                if (stamina > maxStamina){
                    stamina = maxStamina;
                }
            }
        }
        return tempMoveSpeed;
    }

    public boolean isFacingRight(){
        return facingRight;
    }

    public Texture getSkin() {
        return skin;
    }

    private void flipSkinX(){
        sprite.flip(true, false);
        facingRight = !facingRight;
    }

    private boolean canDodge(){
        if(isDodging) return false;
        else if(stamina < 40f) return false;

        return true;
    }

    public void trackPlayerMovement() {
        Gdx.app.log("Stamina", String.valueOf(stamina));
        if (!isDodging){
            controlPlayer();
        }
        else{
            xPos += getMoveSpeed() * Gdx.graphics.getDeltaTime() * xMovement;
            yPos += getMoveSpeed() * Gdx.graphics.getDeltaTime() * yMovement;

            xDistance = (xPos - dodgeOriginX) * (xPos - dodgeOriginX);
            yDistance = (yPos - dodgeOriginY) * (yPos - dodgeOriginY);
            totalDistance = (float) Math.sqrt(yDistance + xDistance);

            if (totalDistance>dodgeDistance){
                isDodging = false;
            }
        }
    }

    private void controlPlayer(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xPos -= getMoveSpeed() * Gdx.graphics.getDeltaTime();
            if (isFacingRight()) {
                flipSkinX();
            }
            xMovement = -1;
        } else {
            xMovement = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xPos += getMoveSpeed() * Gdx.graphics.getDeltaTime();
            if (!isFacingRight()) {
                flipSkinX();
            }
            xMovement = 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yPos += getMoveSpeed() * Gdx.graphics.getDeltaTime();
            yMovement = 1;
        } else {
            yMovement = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yPos -= getMoveSpeed() * Gdx.graphics.getDeltaTime();
            yMovement = -1;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && canDodge()) {
            dodge();
        }
    }

    private void dodge(){
        isDodging = true;
        stamina -= 40f;
        dodgeOriginX = xPos;
        dodgeOriginY = yPos;
    }
}

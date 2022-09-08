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

    private Weapon shotgun;
    private Weapon bayonet;
    private float stamina;
    private float maxStamina;
    private float baseMoveSpeed;
    private boolean facingRight;
    private float tempMoveSpeed;
    boolean isDodging;

    public PlayerCharacter(){
        this.skin = new Texture("manny.png");
        this.sprite = new Sprite(skin);
        this.xPos = 600;
        this.yPos = 310;
        this.baseMoveSpeed = 120.0f;
        this.facingRight = false;
        this.maxStamina = 100f;
        this.stamina = maxStamina;
    }

    public float getBaseMoveSpeed() {
        tempMoveSpeed = baseMoveSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && stamina <= 0f){
            stamina = 0f;
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && stamina > 0f){
                stamina -= 40f * Gdx.graphics.getDeltaTime();
                tempMoveSpeed = baseMoveSpeed * 2f;
            }
            else {
                if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                    tempMoveSpeed = baseMoveSpeed * 0.5f;
                }
                stamina += 40f * Gdx.graphics.getDeltaTime();
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

    public void flipSkinX(){
        sprite.flip(true, false);
        facingRight = !facingRight;
    }

    public boolean canDodge(){
        if(isDodging) return false;
        else if(stamina < maxStamina/3) return false;

        return true;
    }

}

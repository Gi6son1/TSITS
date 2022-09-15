package com.tsits.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;

public class PlayerCharacter {
    private Texture skin;
    public Sprite sprite; //sprite object for drawing onto window

    //these are updated and passed to Game class for drawing
    public float xPos;
    public float yPos;
    float rotation = 0f;

    public Weapon weapon;

    private float stamina;
    private float maxStamina;
    private float staminaRegenSpeed;
    private float baseMoveSpeed;
    private boolean facingRight;

    private boolean isDodging;
    private float dodgeDistance; //how far the player can dodge
    private int xMovement; //use to find the direction of movement to dodge the right way
    private int yMovement;

    private float dodgeOriginX; //used to calculate distance dodged
    private float dodgeOriginY;

    private final float DODGE_MULTIPLIER = 6f;
    private final float AIM_MULTIPLIER= 0.5f;
    private final float SPRINT_MULTIPLIER = 2f;


    public PlayerCharacter() {
        this.skin = new Texture("manny.png");
        this.sprite = new Sprite(skin);

        this.sprite.setOrigin(sprite.getWidth() * 0.8f, sprite.getHeight()/2 - 30f);
        this.sprite.setCenterX(sprite.getWidth() * 0.8f);
        this.xPos = 600; //starting point on window
        this.yPos = 310;
        this.weapon = new Weapon(xPos, yPos);

        this.baseMoveSpeed = 150.0f;
        this.facingRight = false;
        this.maxStamina = 100f; //these can be upgraded using power-ups
        this.stamina = maxStamina;
        this.staminaRegenSpeed = 25f;
        this.dodgeDistance = 75f;
    }

    public void trackPlayerMovement() { //used in game class
        //Gdx.app.log("Stamina", String.valueOf(stamina)); //display stamina on console for debugging
        weapon.updatePositon(xPos, yPos);

        if (!isDodging) { //if player isn't in the middle of dodge, they can move around
            controlPlayer();
        }
        else { //if they dodgin'
            //if (xMovement+yMovement == 0) {//ENTER CODE} //TODO IF PLAYER IS NOT MOVING, MOVE IN DIRECTION OF MOUSE? Need feedback

            xPos += getMoveSpeed() * Gdx.graphics.getDeltaTime() * xMovement; //continously keep track of their location
            yPos += getMoveSpeed() * Gdx.graphics.getDeltaTime() * yMovement;

            float totalDistance = (float) Point2D.distance(xPos, yPos, dodgeOriginX, dodgeOriginY); //find total distance in respect to origin dodge location

            if (totalDistance > dodgeDistance) { //once they reach dodging distance, stop the dodging action
                isDodging = false;
            }
        }
    }

    private float getMoveSpeed() { //used to return determined speed of player movements
        float tempMoveSpeed = baseMoveSpeed;

        if (isDodging) { //if dodging move 6x as fast
            return tempMoveSpeed * DODGE_MULTIPLIER;
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) { //if aiming, move half speed
            tempMoveSpeed = baseMoveSpeed * AIM_MULTIPLIER;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) { //if sprinting with no stamina, stay walking speed
            if (stamina <= 0f) {
                stamina = 0f;
            } else {
                stamina -= 30f * Gdx.graphics.getDeltaTime(); //if with stamina, lose some and go double speed
                tempMoveSpeed = baseMoveSpeed * SPRINT_MULTIPLIER;
            }
        }

        return tempMoveSpeed; //return the final player speed
    }

    private void controlPlayer() { //used to give user control of player movement
        if (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) { //if player not sprinting
            stamina += staminaRegenSpeed * Gdx.graphics.getDeltaTime(); //stamina regenerates
            if (stamina > maxStamina) {
                stamina = maxStamina;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { //move left
            xPos -= getMoveSpeed() * Gdx.graphics.getDeltaTime();
            if (facingRight) {
                flipSkinX();
            }
            xMovement = -1; //log  direction of movement
        } else {
            xMovement = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) { //move right
            xPos += getMoveSpeed() * Gdx.graphics.getDeltaTime();
            if (!facingRight) {
                flipSkinX();
            }
            xMovement = 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) { //move up
            yPos += getMoveSpeed() * Gdx.graphics.getDeltaTime();
            yMovement = 1;
        } else {
            yMovement = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) { //move down
            yPos -= getMoveSpeed() * Gdx.graphics.getDeltaTime();
            yMovement = -1;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && canDodge()) { //dodge (if allowed)
            startDodge();
        }
    }

    private void startDodge() { //method to prepare variables for dodge move
        isDodging = true;
        stamina -= 40f;
        dodgeOriginX = xPos; //log original position before dodge
        dodgeOriginY = yPos;
    }

    private void flipSkinX() { //flip sprite on x-axis
        sprite.flip(true, false);
        facingRight = !facingRight;
    }

    private boolean canDodge() { //checks if dodge is possible
        if (isDodging) return false; //can't do another one if already doing one
        else if (stamina < 40f) return false;

        return true;
    }
}

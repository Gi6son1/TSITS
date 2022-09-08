package com.tsits.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public class Weapon {
    private Texture skin;
    Sprite sprite;

    public float xPos;
    public float yPos;
    public float rotation;


    private float rangedDmg;
    private float meleeDmg;

    public Weapon(float xPos, float yPos){
        this.skin = new Texture("mannysmachete.png");
        this.sprite = new Sprite(skin);
        this.sprite.setOrigin(sprite.getWidth()-15f, sprite.getHeight()/2 + 5);
        this.sprite.setCenter();
        updatePositon(xPos, yPos);
        rotation = 0f;
    }

    public void updatePositon(float xPos, float yPos){
        this.xPos = xPos - 20;
        this.yPos = yPos + 20;

        rotateToMouse();
    }

    private void rotateToMouse(){
        float mouseY = Gdx.input.getY();
        float mouseX = Gdx.input.getX();


        float yDis = (720 - yPos - mouseY);
        float xDis = (mouseX - xPos);


        Vector2 vector2 = new Vector2();
        vector2.x = xDis;
        vector2.y = yDis;

 //TODO centre a point of rotation for the gun and character so that they sync up with the mouse movement


        rotation = 180 + vector2.angleDeg();
        Gdx.app.log("Angle", String.valueOf(rotation));

    }
}

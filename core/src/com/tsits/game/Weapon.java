package com.tsits.game;

import com.badlogic.gdx.graphics.Texture;

public class Weapon {
    private Texture skin;
    private int damage;

    public void increaseDamage(float dmgMultiplier) {
        this.damage *= dmgMultiplier;
    }
}

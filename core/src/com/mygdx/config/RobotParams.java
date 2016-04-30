package com.mygdx.config;

import com.badlogic.gdx.math.Vector2;

public class RobotParams {
    public String name;
    public float health;
    public float cooldown;
    public float width;
    public float height;
    public Vector2 bulletVelocity;
    public float damage;

    public RobotParams(String name, float health, float cooldown, float
            bulletVelocityX, float bulletVelocitY, float damage) {
        this.name = name;
        this.health = health;
        this.cooldown = cooldown;
        this.width = 100;
        this.height = 100;
        this.bulletVelocity = new Vector2(bulletVelocityX, bulletVelocitY);
        this.damage = damage;
    }
}

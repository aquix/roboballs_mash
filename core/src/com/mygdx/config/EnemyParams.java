package com.mygdx.config;

import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class EnemyParams {
    public String name;
    public float health;
    public float cooldown;
    public float velocity_x;
    public int width;
    public int height;
    public float damage;

    public EnemyParams(String name, float health, float cooldown,
                       float velocity_x, int width, int height, float
                               damage) {
        this.name = name;
        this.health = health;
        this.cooldown = cooldown;
        this.velocity_x = velocity_x;
        this.width = width;
        this.height = height;
        this.damage = damage;
    }
}

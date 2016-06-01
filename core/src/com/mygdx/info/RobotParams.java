package com.mygdx.info;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_objects.map.CellType;

import java.util.ArrayList;

public class RobotParams {
    public String name;
    public float health;
    public float cooldown;
    public int width;
    public int height;
    public Vector2 bulletVelocity;
    public float damage;
    public ArrayList<CellType> cellTypes;
    public float tileCooldown;
    public int cost;

    public RobotParams(String name, float health, float cooldown, Vector2 bulletVelocity,
                       float damage, ArrayList<CellType> cellTypes, float
                               tileCooldown, int cost) {
        this.name = name;
        this.health = health;
        this.cooldown = cooldown;
        this.width = 100;
        this.height = 100;
        this.bulletVelocity = bulletVelocity;
        this.damage = damage;
        this.cellTypes = cellTypes;
        this.tileCooldown = tileCooldown;
        this.cost = cost;
    }
}

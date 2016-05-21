package com.mygdx.game_objects.robots;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.bullets.EnemyBullet;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.map.CellType;
import com.mygdx.game_objects.map.GameMap;

import java.util.ArrayList;

public abstract class Robot extends GameObject {
    protected Vector2 cell;
    protected float leftoverCooldown;
    protected float health;
    protected float cooldown;
    protected ArrayList<CellType> cellTypes;

    public Robot(float x, float y, float width, float height, String name,
                 float cooldown, float health, ArrayList<CellType> cellTypes) {
        super(x, y, width, height);
        cell = new Vector2(-1, -1);
        this.name = name;
        this.cooldown = cooldown;
        this.leftoverCooldown = cooldown;
        this.health = health;
        this.cellTypes = cellTypes;
    }

    public void render(SpriteBatch batcher, float runTime) {
        batcher.draw(AssetLoader.getInstance().robots.get(name)
                .getKeyFrame(runTime), rect.x, rect.y, rect.width, rect.height);
    }

    public void setCell(int i, int j) {
        cell.x = j;
        cell.y = i;
    }

    public Vector2 getCell() {
        return cell;
    }

    public void update(float delta, GameMap map) {
        super.update(delta);
        if (leftoverCooldown > 0) {
            leftoverCooldown -= delta;
            if (leftoverCooldown < 0) {
                leftoverCooldown = 0;
            }
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean makeDamaged(Enemy enemy) {
        health -= enemy.getDamage();
        return true;
    }

    public boolean makeDamaged(IDamagable bullet) {
        health -= bullet.getDamage();
        return true;
    }

    public ArrayList<CellType> getCellTypes() {
        return cellTypes;
    }
}

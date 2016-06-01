package com.mygdx.game_objects.robots;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.IAliveable;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.bullets.EnemyBullet;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.map.CellType;
import com.mygdx.game_objects.map.GameMap;

import java.util.ArrayList;

public abstract class Robot extends GameObject implements IAliveable {
    protected Vector2 cell;
    protected float leftoverCooldown;
    protected float health;
    protected float cooldown;
    protected ArrayList<CellType> cellTypes;
    protected Sprite sprite;
    protected State state;
    protected final float damagedAnimationTime = 0.5f;
    protected float damagedTime = 0;

    public Robot(float x, float y, float width, float height, String name,
                 float cooldown, float health, ArrayList<CellType> cellTypes) {
        super(x, y, width, height);
        cell = new Vector2(-1, -1);
        this.name = name;
        this.cooldown = cooldown;
        this.leftoverCooldown = cooldown;
        this.health = health;
        this.cellTypes = cellTypes;
        this.sprite = new Sprite(AssetLoader.getInstance().robots.get(name)
                .getKeyFrame(0));
        this.state = State.ALIVE;
    }

    public void render(SpriteBatch batcher, float runTime) {
        if (state == State.DAMAGED) {
            float alpha = (float)Math.abs(0.5f * Math.cos(2 * Math.PI *
                    damagedTime)) + 0.5f;
            sprite.setColor(new Color(200, 200, 200, alpha));
        } else {
            sprite.setColor(new Color(255, 255, 255, 1));
        }
        sprite.setRegion(AssetLoader.getInstance().robots.get(name)
                .getKeyFrame(runTime));
        sprite.setPosition(rect.x, rect.y);
        sprite.draw(batcher);
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

        if (state == State.DAMAGED) {
            damagedTime += delta;
            if (damagedTime >= damagedAnimationTime) {
                state = State.ALIVE;
                damagedTime = 0;
            }
        }
        if (health <= 0) {
            state = State.DEAD;
        }
    }

    public boolean isAlive() {
        return state != State.DEAD;
    }

    public boolean makeDamaged(IDamagable damagable) {
        health -= damagable.getDamage();
        state = State.DAMAGED;
        return true;
    }

    public ArrayList<CellType> getCellTypes() {
        return cellTypes;
    }
}

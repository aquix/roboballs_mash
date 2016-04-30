package com.mygdx.game_objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;

public abstract class Robot extends GameObject {
    protected Vector2 cell;
    protected float leftoverCooldown;
    protected float health;
    protected float cooldown;

    public Robot(float x, float y, float width, float height) {
        super(x, y, width, height);
        cell = new Vector2(-1, -1);
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(AssetLoader.getInstance().robots.get(name), rect
                .x, rect.y, rect.width, rect.height);
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
}

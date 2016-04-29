package com.mygdx.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Enemy extends GameObject {
    protected float spawnTime;

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public float getSpawnTime() {
        return spawnTime;
    }
}

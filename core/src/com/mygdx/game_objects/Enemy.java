package com.mygdx.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;

public abstract class Enemy extends GameObject {
    protected float spawnTime;
    protected float health;
    protected float cooldown;
    protected int startLine;
    protected float max_velocity;
    protected EnemyState state;

    public Enemy(float x, float y, float width, float height, float
            spawnTime, int startLine) {
        super(x, y, width, height);
        this.spawnTime = spawnTime;
        this.startLine = startLine;
        state = EnemyState.READY;
    }

    public float getSpawnTime() {
        return spawnTime;
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(AssetLoader.getInstance().enemies.get(name), rect.x,
                rect.y, rect.width, rect.height);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (health <= 0) {
            state = EnemyState.DEAD;
        }
    }

    public void start() {
        velocity.x = max_velocity;
        state = EnemyState.ALIVE;
    }

    public boolean isAlive() {
        return state != EnemyState.DEAD;
    }

    public boolean makeDamaged(Bullet bullet) {
        health -= bullet.getDamage();
        return true;
    }
}

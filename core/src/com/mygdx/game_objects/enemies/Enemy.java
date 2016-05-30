package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.bullets.Bullet;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.bullets.RobotBullet;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;

public abstract class Enemy extends GameObject {
    protected float spawnTime;
    protected float health;
    protected float cooldown;
    protected float leftoverCooldown;
    protected int startLine;
    protected float max_velocity;
    protected State state;
    protected float damage;
    protected Robot aimRobot;
    protected boolean stateChanged;
    protected final float fallAnimationTime = 2;
    protected float fallTime = 0;
    Sprite sprite;

    public Enemy(float x, float y, float width, float height, float
            spawnTime, int startLine, String name) {
        super(x, y, width, height);
        this.spawnTime = spawnTime;
        this.startLine = startLine;
        state = State.READY;
        stateChanged = false;
        this.name = name;
        EnemyParams enemyParams = EnemiesData.get(name);
        cooldown = enemyParams.cooldown;
        leftoverCooldown = cooldown;
        health = enemyParams.health;
        max_velocity = enemyParams.velocity_x;
        damage = EnemiesData.get(name).damage;
        sprite = new Sprite(AssetLoader.getInstance().enemies.get(name).getKeyFrame
                (0));
    }

    public float getSpawnTime() {
        return spawnTime;
    }

    public void render(SpriteBatch batcher, float gameTime) {
        sprite.setRegion(AssetLoader.getInstance().enemies.get(name).getKeyFrame
                (gameTime));
        sprite.setPosition(rect.x, rect.y);
        sprite.setX(rect.x);
        sprite.setY(rect.y);
        sprite.draw(batcher);
    }

    public void update(float delta, GameMap map) {
        super.update(delta);
        if (leftoverCooldown > 0) {
            leftoverCooldown -= delta;
            if (leftoverCooldown < 0) {
                leftoverCooldown = 0;
            }
        }
        if (health <= 0) {
            state = State.FALLING_DOWN;
        }
    }

    public void start() {
        velocity.x = max_velocity;
        state = State.ALIVE;
    }

    public boolean isAlive() {
        return state != State.DEAD;
    }

    public boolean makeDamaged(IDamagable bullet) {
        health -= bullet.getDamage();
        return true;
    }

    public float getDamage() {
        return damage;
    }

    public void kill() {
        this.state = State.DEAD;
    }
}

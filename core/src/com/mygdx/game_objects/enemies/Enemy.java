package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_helpers.AssetLoader;
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
    protected EnemyState state;
    protected float damage;
    protected IEnemyBehaviour behaviour;
    protected Robot aimRobot;
    protected boolean stateChanged;
    protected EnemyState previousState;

    public Enemy(float x, float y, float width, float height, float
            spawnTime, int startLine, String name) {
        super(x, y, width, height);
        this.spawnTime = spawnTime;
        this.startLine = startLine;
        state = EnemyState.READY;
        behaviour = new DefaultSimpleEnemyBehaviour();
        stateChanged = false;
        this.name = name;
        EnemyParams enemyParams = EnemiesData.get(name);
        cooldown = enemyParams.cooldown;
        leftoverCooldown = cooldown;
        health = enemyParams.health;
        max_velocity = enemyParams.velocity_x;
        damage = EnemiesData.get(name).damage;
    }

    public float getSpawnTime() {
        return spawnTime;
    }

    public void render(SpriteBatch batcher, float gameTime) {
        batcher.draw(AssetLoader.getInstance().enemies.get(name).getKeyFrame
                (gameTime), rect.x,
                rect.y, rect.width, rect.height);
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

    public boolean makeDamaged(RobotBullet bullet) {
        health -= bullet.getDamage();
        return true;
    }

    public float getDamage() {
        return damage;
    }

    public void kill() {
        this.state = EnemyState.DEAD;
    }
}

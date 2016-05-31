package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.bullets.Bullet;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.bullets.IceBotBullet;
import com.mygdx.game_objects.bullets.RobotBullet;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;

public abstract class Enemy extends GameObject implements IDamagable {
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
    protected final float damagedAnimationTime = 0.1f;
    protected float damagedTime = 0;
    protected float speedModifier;
    protected float speedModifierTime;

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
        speedModifier = 1;
        speedModifierTime = 0;
    }

    public float getSpawnTime() {
        return spawnTime;
    }

    public void render(SpriteBatch batcher, float gameTime) {
        if (state == State.DAMAGED || state == State.ICE_DAMAGED) {
            float alpha = (float)Math.abs(0.25f * Math.cos(10 * Math.PI *
                    damagedTime)) + 0.75f;
            if (state == State.DAMAGED) {
                sprite.setColor(new Color(1, 0, 0, alpha));
            } else {
                sprite.setColor(new Color(0.188f, 0.643f, 0.298f, alpha));
            }
        } else {
            sprite.setColor(new Color(1, 1, 1, 1));
        }

        if (speedModifier != 1 && state != State.ICE_DAMAGED) {
            sprite.setColor(new Color(0x30a4cfff));
        }

        sprite.setRegion(AssetLoader.getInstance().enemies.get(name).getKeyFrame
                (gameTime));
        sprite.setPosition(rect.x, rect.y);
        sprite.draw(batcher);
    }

    public void update(float delta, GameMap map) {
        speedModifierTime -= delta;
        if (speedModifierTime <= 0) {
            speedModifier = 1;
        }
        if (velocity.x == max_velocity) {
            velocity.x = max_velocity * speedModifier;
        }
        super.update(delta);
        if (leftoverCooldown > 0) {
            leftoverCooldown -= delta;
            if (leftoverCooldown < 0) {
                leftoverCooldown = 0;
            }
        }
        if (state == State.DAMAGED || state == State.ICE_DAMAGED) {
            damagedTime += delta;
            if (damagedTime >= damagedAnimationTime) {
                state = State.ALIVE;
                damagedTime = 0;
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
        state = State.DAMAGED;
        return true;
    }

    public float getDamage() {
        return damage;
    }

    public void kill() {
        this.state = State.DEAD;
    }

    public void changeSpeed(float speedModifier, float speedModifierTime) {
        this.speedModifier = speedModifier;
        this.speedModifierTime = speedModifierTime;
    }

    public void setState(State state) {
        this.state = state;
    }
}

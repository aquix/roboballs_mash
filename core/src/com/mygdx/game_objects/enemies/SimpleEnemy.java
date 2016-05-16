package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;

public class SimpleEnemy extends Enemy {
    public SimpleEnemy(float spawnTime, int startLine) {
        super(-EnemiesData.data.get("SimpleEnemy").width,
                210 + startLine * 100 - EnemiesData.data.get("SimpleEnemy")
                        .height,
                EnemiesData.data.get("SimpleEnemy").width,
                EnemiesData.data.get("SimpleEnemy").height, spawnTime,
                startLine);
        EnemyParams enemyParams = EnemiesData.data.get("SimpleEnemy");
        cooldown = enemyParams.cooldown;
        leftoverCooldown = cooldown;
        health = enemyParams.health;
        max_velocity = enemyParams.velocity_x;
        name = "SimpleEnemy";
        damage = EnemiesData.data.get("SimpleEnemy").damage;

        collisionRect.width = 100;
        collisionRect.height = 100;
        collisionRect.x = rect.x + (rect.width - 100);
        collisionRect.y = rect.y + (rect.height - 100);

        aimRobot = null;
    }

    @Override
    public void update(float delta, GameMap map) {
        boolean isOnGround = false;
        for (Rectangle groundRect : map.getGroundRects()) {
            if (groundRect.overlaps(this.collisionRect) || rect.x < 140) {
                isOnGround = true;
                break;
            }
        }

        if (isOnGround) {
            velocity.y = 0;
        } else {
            velocity.y = 200;
        }

        if (stateChanged) {
            if (state == EnemyState.ALIVE) {
                behaviour = new DefaultSimpleEnemyBehaviour();
            } else if (state == EnemyState.DAMAGING) {
                behaviour = new DamageSimpleEnemyBehaviour();
            }
            stateChanged = false;
        }

        behaviour.update(delta, map, this);
        super.update(delta, map);
    }

    public void render(SpriteBatch batcher, float gameTime) {
        batcher.draw(AssetLoader.getInstance().simpleEnemyAnimation
                .getKeyFrame(gameTime), rect.x, rect.y, rect.width, rect
                .height);
    }

    @Override
    public void render(SpriteBatch batcher) {

    }
}

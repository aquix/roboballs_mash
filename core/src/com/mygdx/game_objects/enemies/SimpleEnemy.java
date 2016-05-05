package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.EnemyState;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.Robot;

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

        if (state == EnemyState.DAMAGING) {
            this.velocity.x = 0;
        } else {
            this.velocity.x = max_velocity;
        }

        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            for (Robot robot : map.getRobots()) {
                damageRobot(robot);
            }
        }
    }

    private void damageRobot(Robot robot) {
        if (leftoverCooldown <= 0) {
            if (this.collisionRect.overlaps(robot.getCollisionRect())) {
                this.state = EnemyState.DAMAGING;
                robot.makeDamaged(this);
                leftoverCooldown = cooldown;

                // If enemy kill robot then continue moving
                if (!robot.isAlive()) {
                    this.state = EnemyState.ALIVE;
                }
            }
        }
    }
}

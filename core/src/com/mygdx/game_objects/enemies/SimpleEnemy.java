package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.EnemyState;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.Robot;

import java.util.ArrayList;

public class SimpleEnemy extends Enemy {
    private Robot aimRobot;
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

        if (state == EnemyState.DAMAGING) {
            this.velocity.x = 0;
        } else {
            this.velocity.x = max_velocity;
        }

        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            if (aimRobot == null) {
                this.state = EnemyState.ALIVE;
            }
            for (Robot robot : map.getRobots()) {
                damageRobot(robot);
            }
        }

        if (aimRobot != null && !aimRobot.isAlive()) {
            aimRobot = null;
        }
    }

    private void damageRobot(Robot robot) {
        boolean damageSomebody = false;
        if (leftoverCooldown <= 0) {
            if (this.collisionRect.overlaps(robot.getCollisionRect()) &&
                    robot.isAlive()) {
                aimRobot = robot;
                this.state = EnemyState.DAMAGING;
                robot.makeDamaged(this);
                leftoverCooldown = cooldown;
                damageSomebody = true;
            }
        }

        if (!damageSomebody) {
            this.state = EnemyState.ALIVE;
            aimRobot = null;
        }
    }
}

package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.google.gwt.aria.client.SpinbuttonRole;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;

public class SimpleEnemy extends Enemy {
    public SimpleEnemy(float spawnTime, int startLine) {
        super(-EnemiesData.get("SimpleEnemy").width,
                210 + startLine * 100 - EnemiesData.get("SimpleEnemy")
                        .height,
                EnemiesData.get("SimpleEnemy").width,
                EnemiesData.get("SimpleEnemy").height, spawnTime,
                startLine, "SimpleEnemy");

        collisionRect.width = 30;
        collisionRect.height = 100;
        collisionRect.x = rect.x + (rect.width - 100);
        collisionRect.y = rect.y + (rect.height - 100);
        state = State.ALIVE;
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

        if (state == State.ALIVE) {
            velocity.x = max_velocity;
            velocity.y = 0;

            for (Robot robot : map.getRobots()) {
                if (collisionRect.overlaps(robot.getCollisionRect())) {
                    aimRobot = robot;
                    state = State.DAMAGING;
                }
            }
        } else if (state == State.DAMAGING) {
            velocity.x = 0;

            if (!aimRobot.isAlive()) {
                aimRobot = null;
                state = State.ALIVE;
                return;
            }

            if (leftoverCooldown <= 0) {
                aimRobot.makeDamaged(this);
                leftoverCooldown = cooldown;
            }
        } else if (state == State.FALLING_DOWN) {
            fallTime += delta;
            if (fallTime >= fallAnimationTime) {
                state = State.DEAD;
            }
            return;
        }

        super.update(delta, map);
    }

    @Override
    public void render(SpriteBatch batcher, float gameTime) {
        if (state == State.ALIVE) {
            super.render(batcher, gameTime);
        } else if (state == State.DAMAGING) {
            // TODO add damage animation
            super.render(batcher, gameTime);
        } else if (state == State.FALLING_DOWN) {
            sprite.setRegion(AssetLoader.getInstance().simpleEnemyFall.getKeyFrame
                            (fallTime));
            sprite.setPosition(rect.x, rect.y);
            sprite.draw(batcher);
        }
    }
}

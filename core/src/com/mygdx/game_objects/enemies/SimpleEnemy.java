package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;

public class SimpleEnemy extends Enemy {
    private final float fallAnimationTime = 2;
    private float fallTime = 0;

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
        if (state == State.FALLING_DOWN) {
            fallTime += delta;
            if (fallTime >= fallAnimationTime) {
                state = State.DEAD;
            }
            return;
        }

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
            if (state == State.ALIVE) {
                behaviour = new DefaultSimpleEnemyBehaviour();
            } else if (state == State.DAMAGING) {
                behaviour = new DamageSimpleEnemyBehaviour();
            }
            stateChanged = false;
        }

        behaviour.update(delta, map, this);
        super.update(delta, map);
    }

    @Override
    public void render(SpriteBatch batcher, float gameTime) {
        if (state == State.ALIVE) {
            super.render(batcher, gameTime);
        } else if (state == State.DAMAGING) {
            // TODO add damage animaiton
            super.render(batcher, gameTime);
        } else if (state == State.FALLING_DOWN) {
            batcher.draw(AssetLoader.getInstance().simpleEnemyFall.getKeyFrame
                            (fallTime), rect.x, rect.y);
        }
    }
}

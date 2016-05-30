package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.map.GameMap;

public abstract class Helicopter extends Enemy {
    public Helicopter(float x, float y, float width, float height, float spawnTime, int startLine, String name) {
        super(x, y, width, height, spawnTime, startLine, name);
    }

    @Override
    public void update(float delta, GameMap map) {
        if (state == State.FALLING_DOWN) {
            acceleration.x = -5;
            acceleration.y = 150;

            velocity.x += acceleration.x * delta;
            velocity.y += acceleration.y * delta;

            for (Rectangle rect : map.getGroundRects()) {
                if (collisionRect.overlaps(rect)) {
                    state = State.DEAD;
                    return;
                }
            }
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
            sprite.rotate(0.5f);
            sprite.setPosition(rect.x, rect.y);
            sprite.draw(batcher);
        }

    }
}

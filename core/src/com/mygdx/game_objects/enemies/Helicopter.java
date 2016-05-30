package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.map.GameMap;

public abstract class Helicopter extends Enemy {
    protected float fallingRotate;

    public Helicopter(float x, float y, float width, float height, float spawnTime, int startLine, String name) {
        super(x, y, width, height, spawnTime, startLine, name);
        fallingRotate = 0;
    }

    @Override
    public void update(float delta, GameMap map) {
        if (state == State.FALLING_DOWN) {
            if (fallingRotate <= 60) {
                fallingRotate += 0.4;
            }
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
            // TODO add damage animaiton
            super.render(batcher, gameTime);
        } else if (state == State.FALLING_DOWN) {
            Sprite sprite = new Sprite(AssetLoader.getInstance().enemies.get(name)
                    .getKeyFrame(gameTime));
            sprite.rotate(fallingRotate);
            sprite.setX(rect.x);
            sprite.setY(rect.y);
            sprite.draw(batcher);
        }

    }
}

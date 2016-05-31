package com.mygdx.game_objects.robots;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.RobotsData;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.map.GameMap;

import java.util.Set;

public class MineBot extends Robot implements IDamagable {
    private float damage;
    private State state;
    private final float exploseAnimationTime = 1;
    private float exploseTime;

    public MineBot(float x, float y) {
        super(x, y, RobotsData.get("MineBot").width,
                RobotsData.get("MineBot").height, "MineBot", RobotsData.get
                        ("MineBot").cooldown, RobotsData.get("MineBot").health,
                RobotsData.get("MineBot").cellTypes);
        damage = RobotsData.get("MineBot").damage;
        state = State.ALIVE;
        exploseTime = 0;
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (state == State.ALIVE) {
            if (leftoverCooldown <= 0) {
                Vector2 cellPosition = map.getCellIndexes(rect.x + 10, rect.y + 10);
                Set<Enemy> enemiesInCell = map.getCells()[(int)cellPosition.y][
                        (int)cellPosition.x]
                        .getEnemies();
                if (!enemiesInCell.isEmpty()) {
                    for (Enemy enemy : enemiesInCell) {
                        enemy.makeDamaged(this);
                        state = State.FALLING_DOWN;
                        AssetLoader.getInstance().explosionSound.play();
                    }
                }
            }
        } else if (state == State.FALLING_DOWN) {
            exploseTime += delta;
            if (exploseTime >= exploseAnimationTime) {
                state = State.DEAD;
            }
        } else {
            health = 0;
        }
    }

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public void render(SpriteBatch batcher, float runTime) {
        if (state == State.ALIVE) {
            super.render(batcher, runTime);
        } else if (state == State.FALLING_DOWN) {
            batcher.draw(AssetLoader.getInstance().mineBotExplosing
                    .getKeyFrame(exploseTime), rect.x, rect.y, rect.width, rect.height);
            batcher.draw(AssetLoader.getInstance().explose.getKeyFrame
                    (exploseTime), rect.x + 16, rect.y + 16);
        }
    }
}

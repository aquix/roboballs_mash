package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.EnemiesData;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.bullets.HelicopterEnemyBomb;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.map.MapCell;

public class HelicopterBombEnemy extends Enemy {
    public HelicopterBombEnemy(float spawnTime, int startLine) {
        super(-EnemiesData.get("HelicopterBombEnemy").width,
                210 + startLine * 100 - EnemiesData.get("HelicopterBombEnemy")
                        .height,
                EnemiesData.get("HelicopterBombEnemy").width,
                EnemiesData.get("HelicopterBombEnemy").height, spawnTime,
                startLine, "HelicopterBombEnemy");
    }

    @Override
    public void update(float delta, GameMap map) {
        if (state == State.FALLING_DOWN) {
            state = State.DEAD;
            return;
        }

        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            Vector2 currentCell = map.getCellIndexes(rect.x + rect.width, rect.y);
            if (currentCell == null) {
                return;
            }

            for (int i = 0; i < 5; i++) {
                if (map.getCells()[i][(int)currentCell.x].getRobot() != null) {
                    map.addNewBullet(new HelicopterEnemyBomb(rect.x + 40, rect.y
                            + 80));
                    leftoverCooldown = cooldown;
                }
            }
        }
    }
}

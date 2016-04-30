package com.mygdx.level_infrastructure;

import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.enemies.SimpleEnemy;

import java.util.ArrayList;

public class LevelFactory {
    public static Level createLevel(int levelNumber) {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        switch (levelNumber) {
            case 1:
                enemies.add(new SimpleEnemy(2, 4));
                enemies.add(new SimpleEnemy(4, 3));
                enemies.add(new SimpleEnemy(7, 0));

                return new Level(levelNumber, enemies);
        }
        return null;
    }
}

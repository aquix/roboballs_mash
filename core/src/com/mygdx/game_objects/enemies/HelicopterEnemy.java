package com.mygdx.game_objects.enemies;

import com.mygdx.config.EnemiesData;
import com.mygdx.game_objects.map.GameMap;

public class HelicopterEnemy extends Enemy {
    public HelicopterEnemy(float spawnTime, int startLine) {
        super(-EnemiesData.get("HelicopterEnemy").width,
                210 + startLine * 100 - EnemiesData.get("HelicopterEnemy")
                        .height,
                EnemiesData.get("HelicopterEnemy").width,
                EnemiesData.get("HelicopterEnemy").height, spawnTime,
                startLine, "HelicopterEnemy");
    }
}

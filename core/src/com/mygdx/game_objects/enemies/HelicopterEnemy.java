package com.mygdx.game_objects.enemies;

import com.mygdx.info.EnemiesData;

public class HelicopterEnemy extends Helicopter {
    public HelicopterEnemy(float spawnTime, int startLine) {
        super(-EnemiesData.get("HelicopterEnemy").width,
                210 + startLine * 100 - EnemiesData.get("HelicopterEnemy")
                        .height,
                EnemiesData.get("HelicopterEnemy").width,
                EnemiesData.get("HelicopterEnemy").height, spawnTime,
                startLine, "HelicopterEnemy");
    }
}

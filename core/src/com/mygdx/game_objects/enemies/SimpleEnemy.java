package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_objects.Enemy;

public class SimpleEnemy extends Enemy {

    public SimpleEnemy(float spawnTime, int startLine) {
        super(-EnemiesData.data.get("SimpleEnemy").width,
                210 + startLine * 100 - EnemiesData.data.get("SimpleEnemy")
                        .height,
                EnemiesData.data.get("SimpleEnemy").width,
                EnemiesData.data.get("SimpleEnemy").height, spawnTime,
                startLine);
        EnemyParams enemyParams = EnemiesData.data.get("SimpleEnemy");
        cooldown = enemyParams.cooldown;
        health = enemyParams.health;
        max_velocity = enemyParams.velocity_x;
        name = "SimpleEnemy";
    }
}

package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.EnemyParams;
import com.mygdx.game_objects.Enemy;

public class SimpleEnemy extends Enemy {

    public SimpleEnemy(float x, float y, float width, float height, float
            spawnTime, int startLine) {
        super(x, y, width, height, spawnTime, startLine);
        EnemyParams enemyParams = EnemiesData.data.get("SimpleEnemy");
        cooldown = enemyParams.cooldown;
        health = enemyParams.health;
        velocity = new Vector2(enemyParams.velocity_x, 0);
    }
}

package com.mygdx.game_objects.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.EnemiesData;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.map.GameMap;

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

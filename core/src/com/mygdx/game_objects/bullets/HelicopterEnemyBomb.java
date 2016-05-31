package com.mygdx.game_objects.bullets;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.EnemiesData;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.map.GameMap;

public class HelicopterEnemyBomb extends EnemyBullet {
    private Vector2 acceleration;
    public HelicopterEnemyBomb(float x, float y) {
        super(x, y, 51, 61);
        name = "HelicopterEnemyBomb";
        velocity = new Vector2(50, 0);
        damage = EnemiesData.get("HelicopterBombEnemy").damage;
        acceleration = new Vector2(-1, 80);
    }

    @Override
    public void update(float delta, GameMap map) {
        velocity.x += acceleration.x * delta;
        velocity.y += acceleration.y * delta;
        super.update(delta);

        for (Rectangle groundRect : map.getGroundRects()) {
            if (rect.overlaps(groundRect)) {
                this.isActive = false;
                AssetLoader.getInstance().explosionSound.play();
                break;
            }
        }
    }
}

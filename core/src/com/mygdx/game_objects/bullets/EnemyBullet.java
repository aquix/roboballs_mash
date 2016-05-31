package com.mygdx.game_objects.bullets;

import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.robots.Robot;

public class EnemyBullet extends Bullet {
    public EnemyBullet(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void damageRobot(Robot robot) {
        boolean successDamage = robot.makeDamaged(this);
        if (successDamage) {
            AssetLoader.getInstance().explosionSound.play();
            this.isActive = false;
        }
    }
}

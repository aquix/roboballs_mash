package com.mygdx.game_objects.bullets;

import com.mygdx.game_objects.enemies.Enemy;

public class RobotBullet extends Bullet {
    public RobotBullet(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void damageEnemy(Enemy enemy) {
        boolean successDamage = enemy.makeDamaged(this);
        if (successDamage) {
            this.isActive = false;
        }
    }
}

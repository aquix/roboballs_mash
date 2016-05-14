package com.mygdx.game_objects.enemies;

import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;

public class DamageSimpleEnemyBehaviour implements IEnemyBehaviour {
    @Override
    public void update(float delta, GameMap map, Enemy self) {
        self.setVelocity(0, 0);

        if (!self.aimRobot.isAlive()) {
            self.aimRobot = null;
            self.state = EnemyState.ALIVE;
            self.behaviour = new DefaultSimpleEnemyBehaviour();
            return;
        }

        if (self.leftoverCooldown <= 0) {
            self.aimRobot.makeDamaged(self);
            self.leftoverCooldown = self.cooldown;
        }
    }
}

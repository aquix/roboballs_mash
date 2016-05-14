package com.mygdx.game_objects.enemies;

import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.Robot;


public class DefaultSimpleEnemyBehaviour implements IEnemyBehaviour {
    @Override
    public void update(float delta, GameMap map, Enemy self) {
        self.setVelocity(self.max_velocity, 0);

        for (Robot robot : map.getRobots()) {
            if (self.getCollisionRect().overlaps(robot.getCollisionRect())) {
                self.aimRobot = robot;
                self.state = EnemyState.DAMAGING;
                self.behaviour = new DamageSimpleEnemyBehaviour();
            }
        }
    }
}

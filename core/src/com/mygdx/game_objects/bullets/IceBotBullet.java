package com.mygdx.game_objects.bullets;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.State;
import com.mygdx.game_objects.enemies.Enemy;

public class IceBotBullet extends RobotBullet {
    private float speedModifier;
    private float speedModifierTime;

    public IceBotBullet(float x, float y) {
        super(x, y, 105, 54);
        name = "IceBotBullet";
        velocity = RobotsData.get("IceBot").bulletVelocity;
        damage = RobotsData.get("IceBot").damage;
        speedModifier = 0.5f;
        speedModifierTime = 3f;
    }

    public float getSpeedModifier() {
        return speedModifier;
    }

    public float getSpeedModifierTime() {
        return speedModifierTime;
    }

    @Override
    public void damageEnemy(Enemy enemy) {
        super.damageEnemy(enemy);
        enemy.changeSpeed(speedModifier, speedModifierTime);
        enemy.setState(State.ICE_DAMAGED);
    }
}

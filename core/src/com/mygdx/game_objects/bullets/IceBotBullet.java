package com.mygdx.game_objects.bullets;

import com.mygdx.config.RobotsData;

public class IceBotBullet extends RobotBullet {
    public IceBotBullet(float x, float y) {
        super(x, y, 105, 54);
        name = "IceBotBullet";
        velocity = RobotsData.get("IceBot").bulletVelocity;
        damage = RobotsData.get("IceBot").damage;
    }
}

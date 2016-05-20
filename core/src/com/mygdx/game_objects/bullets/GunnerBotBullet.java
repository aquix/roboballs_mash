package com.mygdx.game_objects.bullets;

import com.mygdx.config.RobotsData;

public class GunnerBotBullet extends RobotBullet {

    public GunnerBotBullet(float x, float y) {
        super(x, y, 50, 32);
        name = "GunnerBotBullet";
        velocity = RobotsData.get("GunnerBot").bulletVelocity;
        damage = RobotsData.get("GunnerBot").damage;
    }
}

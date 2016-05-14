package com.mygdx.game_objects.bullets;

import com.mygdx.config.RobotsData;

public class GunnerBotBullet extends Bullet {

    public GunnerBotBullet(float x, float y) {
        super(x, y, 50, 32);
        name = "GunnerBotBullet";
        velocity = RobotsData.getInstance().getData().get("GunnerBot")
                .bulletVelocity;
        damage = RobotsData.getInstance().getData().get("GunnerBot").damage;
    }
}

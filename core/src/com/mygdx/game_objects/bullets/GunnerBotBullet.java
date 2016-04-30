package com.mygdx.game_objects.bullets;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.Bullet;

public class GunnerBotBullet extends Bullet {

    public GunnerBotBullet(float x, float y, float width, float height) {
        super(x, y, width, height);
        name = "GunnerBotBullet";
        velocity = RobotsData.data.get("GunnerBot").bulletVelocity;
    }
}

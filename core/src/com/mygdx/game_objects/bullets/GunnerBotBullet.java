package com.mygdx.game_objects.bullets;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.Bullet;

public class GunnerBotBullet extends Bullet {

    public GunnerBotBullet(float x, float y) {
        super(x, y, 20, 20);
        name = "GunnerBotBullet";
        velocity = RobotsData.data.get("GunnerBot").bulletVelocity;
        damage = RobotsData.data.get("GunnerBot").damage;
    }
}

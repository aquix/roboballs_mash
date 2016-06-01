package com.mygdx.game_objects.bullets;

import com.mygdx.info.RobotsData;
import com.mygdx.game_helpers.AssetLoader;

public class GunnerBotBullet extends RobotBullet {

    public GunnerBotBullet(float x, float y) {
        super(x, y, 50, 32);
        name = "GunnerBotBullet";
        velocity = RobotsData.get("GunnerBot").bulletVelocity;
        damage = RobotsData.get("GunnerBot").damage;
        AssetLoader.getInstance().shotSound.play();
    }
}

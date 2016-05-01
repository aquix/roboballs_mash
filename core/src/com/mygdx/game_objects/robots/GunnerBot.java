package com.mygdx.game_objects.robots;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.Robot;
import com.mygdx.game_objects.bullets.GunnerBotBullet;

public class GunnerBot extends Robot {
    public GunnerBot(float x, float y) {
        super(x, y, RobotsData.data.get("GunnerBot").width, RobotsData.data
                .get("GunnerBot").height, "GunnerBot", RobotsData.data.get
                ("GunnerBot").cooldown, RobotsData.data.get("GunnerBot")
                .health, RobotsData.data.get("GunnerBot").cellTypes);
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            if (!map.isLineEmpty((int)cell.y)) {
                map.addNewBullet(new GunnerBotBullet(rect.x, rect.y));
                leftoverCooldown = cooldown;
            }
        }
    }


}

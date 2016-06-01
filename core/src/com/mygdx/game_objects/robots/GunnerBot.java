package com.mygdx.game_objects.robots;

import com.mygdx.info.RobotsData;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.bullets.GunnerBotBullet;

public class GunnerBot extends Robot {
    public GunnerBot(float x, float y) {
        super(x, y, RobotsData.get("GunnerBot").width,
                RobotsData.get("GunnerBot").height, "GunnerBot", RobotsData.get
                ("GunnerBot").cooldown, RobotsData.get("GunnerBot").health,
                RobotsData.get("GunnerBot").cellTypes);
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            if (!map.isLineEmpty((int)cell.y)) {
                map.addNewBullet(new GunnerBotBullet(rect.x, rect.y + 40));
                leftoverCooldown = cooldown;
            }
        }
    }
}

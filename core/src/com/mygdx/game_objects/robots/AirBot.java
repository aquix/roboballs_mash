package com.mygdx.game_objects.robots;

import com.mygdx.info.RobotsData;
import com.mygdx.game_objects.bullets.GunnerBotBullet;
import com.mygdx.game_objects.map.GameMap;

public class AirBot extends Robot {
    public AirBot(float x, float y) {
        super(x, y, RobotsData.get("AirBot").width,
                RobotsData.get("AirBot").height, "AirBot", RobotsData.get
                        ("AirBot").cooldown, RobotsData.get("AirBot").health,
                RobotsData.get("AirBot").cellTypes);
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

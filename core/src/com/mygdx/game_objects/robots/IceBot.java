package com.mygdx.game_objects.robots;

import com.mygdx.info.RobotsData;
import com.mygdx.game_objects.bullets.IceBotBullet;
import com.mygdx.game_objects.map.GameMap;

public class IceBot extends Robot {
    public IceBot(float x, float y) {
        super(x, y, RobotsData.get("IceBot").width,
                RobotsData.get("IceBot").height, "IceBot", RobotsData.get
                        ("IceBot").cooldown, RobotsData.get("IceBot").health,
                RobotsData.get("IceBot").cellTypes);
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            if (!map.isLineEmpty((int)cell.y)) {
                map.addNewBullet(new IceBotBullet(rect.x, rect.y + 30));
                leftoverCooldown = cooldown;
            }
        }
    }
}

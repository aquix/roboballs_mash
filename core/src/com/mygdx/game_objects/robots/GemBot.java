package com.mygdx.game_objects.robots;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.bullets.GunnerBotBullet;
import com.mygdx.game_objects.collect_items.Gem;
import com.mygdx.game_objects.collect_items.GemType;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.Robot;

public class GemBot extends Robot {
    public GemBot(float x, float y) {
        super(x, y, RobotsData.getInstance().getData().get("GemBot").width, RobotsData
                .getInstance().getData().get("GemBot").height,"GemBot",
                RobotsData.getInstance().getData().get("GemBot")
                .cooldown, RobotsData.getInstance().getData().get("GemBot").health,
                RobotsData
                .getInstance().getData().get("GemBot").cellTypes);
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            map.produceGem(new Gem(rect.x + 40, rect.y + 80, GemType.LITTLE));
            leftoverCooldown = cooldown;
        }
    }
}

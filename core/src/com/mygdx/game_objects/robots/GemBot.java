package com.mygdx.game_objects.robots;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.bullets.GunnerBotBullet;
import com.mygdx.game_objects.collect_items.Gem;
import com.mygdx.game_objects.collect_items.GemType;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.Robot;

public class GemBot extends Robot {
    public GemBot(float x, float y) {
        super(x, y, RobotsData.data.get("GemBot").width, RobotsData.data
                .get("GemBot").height,"GemBot", RobotsData.data.get("GemBot")
                .cooldown, RobotsData.data.get("GemBot").health, RobotsData
                .data.get("GemBot").cellTypes);
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

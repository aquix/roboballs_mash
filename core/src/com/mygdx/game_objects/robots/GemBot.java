package com.mygdx.game_objects.robots;

import com.mygdx.info.RobotsData;
import com.mygdx.game_objects.collect_items.Gem;
import com.mygdx.game_objects.collect_items.GemType;
import com.mygdx.game_objects.map.GameMap;

public class GemBot extends Robot {
    public GemBot(float x, float y) {
        super(x, y, RobotsData.get("GemBot").width, RobotsData.get("GemBot").height,"GemBot",
                RobotsData.get("GemBot").cooldown, RobotsData.get("GemBot").health,
                RobotsData.get("GemBot").cellTypes);
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            double randGem = Math.random();
            GemType type;
            if (randGem <= 0.2) {
                type = GemType.BIG;
            } else {
                type = GemType.LITTLE;
            }
            map.produceGem(new Gem(rect.x + 18, rect.y + 36, type));
            leftoverCooldown = cooldown;
        }
    }
}

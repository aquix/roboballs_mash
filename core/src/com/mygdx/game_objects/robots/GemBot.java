package com.mygdx.game_objects.robots;

import com.mygdx.config.RobotsData;
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

    }
}

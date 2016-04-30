package com.mygdx.game_objects.robots;

import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.GameMap;
import com.mygdx.game_objects.Robot;

public class GemBot extends Robot {
    public GemBot(float x, float y) {
        super(x, y, RobotsData.data.get("GemBot").width, RobotsData.data
                .get("GemBot").height);
        name = "GemBot";
        cooldown = RobotsData.data.get("GemBot").cooldown;
        health = RobotsData.data.get("GemBot").health;
    }

    @Override
    public void update(float delta, GameMap map) {

    }
}

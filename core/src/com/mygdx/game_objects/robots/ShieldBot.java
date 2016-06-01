package com.mygdx.game_objects.robots;

import com.mygdx.info.RobotsData;

public class ShieldBot extends Robot {
    public ShieldBot(float x, float y) {
        super(x, y, RobotsData.get("ShieldBot").width,
                RobotsData.get("ShieldBot").height, "ShieldBot", RobotsData.get
                        ("ShieldBot").cooldown, RobotsData.get("ShieldBot").health,
                RobotsData.get("ShieldBot").cellTypes);
    }
}

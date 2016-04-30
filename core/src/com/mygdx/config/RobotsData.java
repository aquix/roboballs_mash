package com.mygdx.config;

import java.util.HashMap;

public class RobotsData {
    public static HashMap<String, RobotParams> data;

    static {
        data = new HashMap<String, RobotParams>();
        data.put("GunnerBot", new RobotParams("GunnerBot", 6, 2.5f, -200, 0,
                1));
        data.put("GemBot", new RobotParams("GemBot", 6, 0, 0, 0, 0));
    }
}

package com.mygdx.config;

import java.util.HashMap;

public class RobotsData {
    public static HashMap<String, RobotParams> data;

    static {
        data = new HashMap<String, RobotParams>();
        data.put("GunnerBot", new RobotParams("GunnerBot", 6, 1, -200, 0));
        data.put("GemBot", new RobotParams("GunnerBot", 6, 0, 0, 0));
    }
}

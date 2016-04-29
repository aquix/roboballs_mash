package com.mygdx.config;

import java.util.HashMap;

public class RobotsData {
    public static HashMap<String, RobotParams> data;

    static {
        data.put("GunnerBot", new RobotParams("GunnerBot", 6, 1));
        data.put("GemBot", new RobotParams("GunnerBot", 6, 0));
    }
}

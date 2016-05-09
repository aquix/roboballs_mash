package com.mygdx.config;

import com.mygdx.game_objects.map.CellType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class RobotsData {
    private static RobotsData instance;

    private RobotsData() {
        data = new HashMap<String, RobotParams>();
        data.put("GunnerBot", new RobotParams("GunnerBot", 6, 2.5f, -200, 0,
                1, new ArrayList<CellType>(Collections.singletonList(CellType
                .GROUND)), 5, 20));
        data.put("GemBot", new RobotParams("GemBot", 6, 6, 0, 0, 0, new
                ArrayList<CellType>(Collections.singletonList(CellType
                .GROUND)), 3, 10));
    }

    public static RobotsData getInstance() {
        if (instance == null) {
            instance = new RobotsData();
        }
        return instance;
    }
    private HashMap<String, RobotParams> data;

    public HashMap<String, RobotParams> getData() {
        return data;
    }
}

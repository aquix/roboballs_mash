package com.mygdx.config;

import com.mygdx.game_objects.map.CellType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class RobotsData {
    public static HashMap<String, RobotParams> data;

    static {
        data = new HashMap<String, RobotParams>();
        data.put("GunnerBot", new RobotParams("GunnerBot", 6, 2.5f, -200, 0,
                1, new ArrayList<CellType>(Collections.singletonList(CellType
                .GROUND))));
        data.put("GemBot", new RobotParams("GemBot", 6, 6, 0, 0, 0, new
                ArrayList<CellType>(Collections.singletonList(CellType.GROUND))));
    }
}

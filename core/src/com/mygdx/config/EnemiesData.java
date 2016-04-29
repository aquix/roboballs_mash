package com.mygdx.config;

import java.util.HashMap;

public class EnemiesData {
    public static HashMap<String, EnemyParams> data;

    static {
        data.put("SimpleEnemy", new EnemyParams("SimpleEnemy", 4, 0.5f, 50));
    }
}

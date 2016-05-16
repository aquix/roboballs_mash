package com.mygdx.config;

import java.util.HashMap;

// TODO make singleton
public class EnemiesData {
    public static HashMap<String, EnemyParams> data;

    static {
        data = new HashMap<String, EnemyParams>();
        data.put("SimpleEnemy", new EnemyParams("SimpleEnemy", 4, 1f, 50,
                190, 150, 1));
    }
}

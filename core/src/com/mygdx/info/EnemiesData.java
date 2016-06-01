package com.mygdx.info;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


public class EnemiesData {
    private static HashMap<String, EnemyParams> data;

    public static EnemyParams get(String name) {
        return data.get(name);
    }

    public static void createEnemyParams
            (List<EnemyParams> enemyParamsList) {
        data = new HashMap<String,
                EnemyParams>();
        for (EnemyParams enemyParams : enemyParamsList) {
            data.put(enemyParams.name, enemyParams);
        }
    }

    static {
        try {
            Reader reader = new FileReader(String.valueOf(Gdx.files.internal
                    ("enemies/enemy_params.json")));

            Gson gson = new Gson();
            Type enemyParamsListType = new TypeToken<List<EnemyParams>>(){}.getType();
            List<EnemyParams> enemyParamsList = gson.fromJson(reader,
                    enemyParamsListType);
            createEnemyParams(enemyParamsList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

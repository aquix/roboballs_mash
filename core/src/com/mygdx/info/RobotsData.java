package com.mygdx.info;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mygdx.game_objects.map.CellType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

class RobotParamsCreator {
    public String name;
    public float health;
    public float cooldown;
    public int width;
    public int height;
    public float bulletVelocityX;
    public float bulletVelocityY;
    public float damage;
    public String[] cellTypes;
    public float tileCooldown;
    public int cost;

    public RobotParams createRobotParams() {
        // Create cell types
        ArrayList<CellType> cells = new ArrayList<CellType>();
        for (String cellType : cellTypes) {
            if (cellType.equals("GROUND")) {
                cells.add(CellType.GROUND);
            } else if (cellType.equals("AIR")) {
                cells.add(CellType.AIR);
            } else if (cellType.equals("WATER")) {
                cells.add(CellType.WATER);
            }
        }

        // Create velocity vector
        Vector2 bulletVelocity = new Vector2(bulletVelocityX, bulletVelocityY);

        return new RobotParams(name, health, cooldown, bulletVelocity,
                damage, cells, tileCooldown, cost);
    }
}

public class RobotsData {
    private static HashMap<String, RobotParams> data;

    public static RobotParams get(String name) {
        return data.get(name);
    }

    public static void createRobotParams (List<RobotParams> enemyParamsList) {
        data = new HashMap<String, RobotParams>();
        for (RobotParams enemyParams : enemyParamsList) {
            data.put(enemyParams.name, enemyParams);
        }
    }

    static {
        try {
            Reader reader = new FileReader(String.valueOf(Gdx.files.internal
                    ("robots/robot_params.json")));

            Gson gson = new Gson();
            Type robotParamsCreatorListType = new
                    TypeToken<List<RobotParamsCreator>>(){}.getType();
            List<RobotParamsCreator> robotParamsCreatorList = gson.fromJson(reader,
                    robotParamsCreatorListType);

            List<RobotParams> robotParamsList = new ArrayList<RobotParams>();
            for (RobotParamsCreator robotParamsCreator :
                    robotParamsCreatorList) {
                robotParamsList.add(robotParamsCreator.createRobotParams());
            }
            createRobotParams(robotParamsList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

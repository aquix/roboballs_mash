package com.mygdx.level_infrastructure;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.enemies.SimpleEnemy;

import java.io.*;
import java.util.ArrayList;

class EnemyCreator {
    private String name;
    private int spawnTime;
    private int startLine;

    public String getName() {
        return name;
    }

    public int getSpawnTime() {
        return spawnTime;
    }

    public int getStartLine() {
        return startLine;
    }
}

class LevelCreator {
    private int levelNumber;
    private int startGems;
    private String levelMap;
    private ArrayList<EnemyCreator> enemies;

    public Level getLevel() {
        ArrayList<Enemy> result_enemies = new ArrayList<Enemy>();
        for (EnemyCreator enemy : enemies) {
            if (enemy.getName().equals("SimpleEnemy")) {
                result_enemies.add(new SimpleEnemy(enemy.getSpawnTime(),
                        enemy.getStartLine()));
            }
        }

        return new Level(levelNumber, result_enemies, startGems, levelMap);
    }
}

public class LevelFactory {
    public static Level createLevel(int levelNumber) {

        Gson gson = new Gson();

        try {
            Reader reader = new FileReader(String.valueOf(Gdx.files
                    .internal("levels/" + Integer.toString(levelNumber) +
                            ".json")));

            String s = String.valueOf(Gdx.files
                            .internal("levels/" +
                                    Integer.toString(levelNumber) + ".json"));

            LevelCreator levelCreator = (LevelCreator) gson.fromJson(reader,
                    LevelCreator.class);
            return levelCreator.getLevel();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        return null;
    }
}

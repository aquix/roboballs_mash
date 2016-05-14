package com.mygdx.level_infrastructure;

import com.mygdx.game_objects.enemies.Enemy;

import java.util.ArrayList;

public class Level {
    private ArrayList<Enemy> enemies;
    private int levelNumber;
    private int startGems;
    private String levelMap;

    public Level(int levelNumber, ArrayList<Enemy> enemies, int startGems,
                 String levelMap) {
        this.levelNumber = levelNumber;
        this.enemies = enemies;
        this.startGems = startGems;
        this.levelMap = levelMap;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getStartGems() {
        return startGems;
    }

    public String getLevelMap() {
        return levelMap;
    }
}

package com.mygdx.level_infrastructure;

import com.mygdx.game_objects.Enemy;

import java.util.ArrayList;

public class Level {
    private ArrayList<Enemy> enemies;
    private int levelNumber;

    public Level(int levelNumber, ArrayList<Enemy> enemies) {
        this.levelNumber = levelNumber;
        this.enemies = enemies;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}

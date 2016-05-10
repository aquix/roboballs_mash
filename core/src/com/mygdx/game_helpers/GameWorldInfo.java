package com.mygdx.game_helpers;

import com.mygdx.game_objects.Bullet;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.gameworld.GameState;
import com.mygdx.gui_objects.SelectRobotPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class GameWorldInfo implements Serializable {
    public TreeSet<Enemy> readyEnemies;
    public GameMap map;
    public SelectRobotPanel selectRobotPanel;
    public ArrayList<Class> availableRobots;
    public float gameTime;
    public ArrayList<Bullet> bullets;
    public GameState gameState;
    public int gemsCount;
    public int lives;
    public int levelNumber;
}

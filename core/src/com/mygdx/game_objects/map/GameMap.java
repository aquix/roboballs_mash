package com.mygdx.game_objects.map;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game_objects.bullets.Bullet;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.robots.Robot;
import com.mygdx.game_objects.collect_items.Gem;

import java.io.Serializable;
import java.util.ArrayList;

public class GameMap implements Serializable {
    private MapCell[][] cells;
    private ArrayList<Bullet> newBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Robot> robots;
    private ArrayList<Rectangle> groundRects;
    private ArrayList<Gem> gems;

    public GameMap(String levelMap) {
        // Create array of cells types
        CellType[][] levelMapArray = new CellType[5][10];
        String[] lines = levelMap.split("\n");
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 10; ++j) {
                switch (lines[i].charAt(j)) {
                    case 'g':
                        levelMapArray[i][j] = CellType.GROUND;
                        break;
                    case 'a':
                        levelMapArray[i][j] = CellType.AIR;
                        break;
                    case 'w':
                        levelMapArray[i][j] = CellType.WATER;
                        break;
                }
            }
        }

        float cellX = 140;
        float cellY = 110;
        float cellSide = 100;

        cells = new MapCell[5][10];
        groundRects = new ArrayList<Rectangle>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new MapCell(cellX, cellY, levelMapArray[i][j]);
                if (levelMapArray[i][j] == CellType.GROUND) {
                    groundRects.add(new Rectangle(cellX, cellY + 100, 100, 1));
                }
                cellX += cellSide;
            }
            cellY += cellSide;
            cellX = 140;
        }

        newBullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        robots = new ArrayList<Robot>();
        gems = new ArrayList<Gem>();
    }

    public Robot getRobot(float x, float y) {
        int i = (int)((y - 110) / 100);
        int j = (int)((x - 140) / 100);
        return cells[i][j].getRobot();
    }

    public boolean plantRobot(Robot robot, float x, float y) {
        if (getRobot(x, y) == null) {
            int i = (int)((y - 110) / 100);
            int j = (int)((x - 140) / 100);
            MapCell cell = cells[i][j];
            if (!robot.getCellTypes().contains(cell.getType())) {
                return false;
            }
            cell.setRobot((robot));
            robot.setCell(i, j);
            robot.setPosition(cell.getX(), cell.getY());
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Enemy> getEnemiesOnLine(int lineIndex) {
        ArrayList<Enemy> resultEnemies = new ArrayList<Enemy>();
        for (MapCell cell: cells[lineIndex]) {
            resultEnemies.addAll(cell.getEnemies());
        }
        return resultEnemies;
    }

    public boolean isLineEmpty(int lineIndex) {
        return getEnemiesOnLine(lineIndex).isEmpty();
    }

    public void produceGem(Gem gem) {
        gems.add(gem);
    }

    // TODO optimize this govnokod
    public void updateCells() {

        for (Enemy enemy : enemies) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    if (cells[i][j].isInCell(enemy.getCollisionRect()) &&
                            enemy.isAlive()) {
                        cells[i][j].addEnemy(enemy);
                    } else {
                        cells[i][j].removeEnemy(enemy);
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (cells[i][j].getRobot() != null && !cells[i][j].getRobot()
                        .isAlive()) {
                    cells[i][j].setRobot(null);
                }
            }
        }
    }

    public void addNewBullet(Bullet bullet) {
        newBullets.add(bullet);
    }

    public ArrayList<Bullet> grabNewBullets() {
        return newBullets;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public ArrayList<Rectangle> getGroundRects() {
        return groundRects;
    }

    public ArrayList<Gem> grabNewGems() {
        return gems;
    }

    public MapCell[][] getCells() {
        return cells;
    }
}

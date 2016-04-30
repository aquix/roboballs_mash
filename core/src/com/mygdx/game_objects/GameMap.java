package com.mygdx.game_objects;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private MapCell[][] cells;
    private ArrayList<Bullet> newBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Robot> robots;

    public GameMap() {
        float cellX = 140;
        float cellY = 110;
        float cellSide = 100;

        cells = new MapCell[5][10];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new MapCell(cellX, cellY);
                cellX += cellSide;
            }
            cellY += cellSide;
            cellX = 140;
        }

        newBullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        robots = new ArrayList<Robot>();
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
        return getEnemiesOnLine(lineIndex).size() == 0;
    }

    // TODO optimize this govnokod
    public void updateCells(List<Enemy> enemies) {

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
}

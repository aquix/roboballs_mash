package com.mygdx.game_objects;


public class GameMap {
    private MapCell[][] cells;

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
            robot.setPosition(cell.getPosition().x, cell.getPosition().y);
            return true;
        } else {
            return false;
        }
    }

}

package com.mygdx.game_objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

public class MapCell {
    private Vector2 position;
    private Robot robot;

    public MapCell(float x, float y) {
        position = new Vector2(x, y);
        robot = null;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}

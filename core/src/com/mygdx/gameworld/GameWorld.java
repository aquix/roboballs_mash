package com.mygdx.gameworld;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.Configuration;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.GameMap;
import com.mygdx.game_objects.Robot;
import com.mygdx.game_objects.robots.GemBot;
import com.mygdx.game_objects.robots.GunnerBot;
import com.mygdx.gui_objects.SelectRobotPanel;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWorld {
    private ArrayList<Robot> robots;
    private ArrayList<Enemy> enemies;
    private GameMap map;
    private SelectRobotPanel selectRobotPanel;
    private ArrayList<Class> availableRobots;
    private Robot selectedRobot;
    private int level;
    private PointerActions action;
    private Rectangle gameField;
    private Rectangle robotBarField;
    private Rectangle statusBarField;

    public GameWorld() {
        robots = new ArrayList<Robot>();
        enemies = new ArrayList<Enemy>();
        map = new GameMap();
        level = 1;
        availableRobots = new ArrayList<Class>(Arrays.asList(GemBot.class,
                GunnerBot.class));

        selectRobotPanel = new SelectRobotPanel(350, 500, 100, 100, availableRobots);
        action = PointerActions.NOTHING;
        selectedRobot = null;

        statusBarField = new Rectangle(0, 0, Configuration.windowWidth, 100);
        gameField = new Rectangle(140, 110, 1000, 500);
        robotBarField = new Rectangle(0, Configuration.windowHeight - 100,
                Configuration.windowWidth, 100);
    }

    public void update(float delta) {
    }

    public boolean addRobot(Robot robot, int x_square, int y_square) {
        return false;
    }

    public void onClick(int x, int y, int button) {
        switch (action) {
            case NOTHING:
                if (statusBarField.contains(x, y)) {

                } else if (gameField.contains(x, y)) {
                    return;
                } else if (robotBarField.contains(x, y)) {
                    Robot newRobot = selectRobotPanel.getRobot(x, y);
                    if (newRobot == null) {
                        return;
                    }

                    action = PointerActions.ADD_ROBOT;
                    selectedRobot = newRobot;
                }

            case ADD_ROBOT:
                // If right mouse button was pressed then cancel robot planting
                if (button == Input.Buttons.RIGHT) {
                    selectedRobot = null;
                    action = PointerActions.NOTHING;
                }

                // If button was pressed on game field then plant robot
                if (gameField.contains(x, y)) {
                    // If cell is empty and we can plant robot - do it
                    // else do nothing
                    if (map.plantRobot(selectedRobot, x, y)) {
                        robots.add(selectedRobot);
                        selectedRobot = null;
                        action = PointerActions.NOTHING;
                    }
                }

            case REMOVE_ROBOT:
                // TODO removing robots
                break;
        }
    }

    public void render(SpriteBatch batcher) {
        if (selectedRobot != null) {
            selectedRobot.render(batcher);
        }
        selectRobotPanel.render(batcher);
        for (Robot robot : robots) {
            robot.render(batcher);
        }
    }

    public void onMove(int x, int y) {
        if (action == PointerActions.ADD_ROBOT) {
            selectedRobot.setPosition(x, y);
        }
    }
}

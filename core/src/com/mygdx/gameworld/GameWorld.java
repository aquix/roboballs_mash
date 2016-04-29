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
            // TODO add map reaction on touch
            case NOTHING:
                if (statusBarField.contains(x, y)) {

                } else if (gameField.contains(x, y)) {

                } else if (robotBarField.contains(x, y)) {
                    Robot newRobot = selectRobotPanel.getRobot(x, y);
                    if (newRobot == null) {
                        return;
                    }

                    action = PointerActions.ADD_ROBOT;
                    selectedRobot = newRobot;
                }

            case ADD_ROBOT:
                if (button == Input.Buttons.RIGHT) {
                    selectedRobot = null;
                    action = PointerActions.NOTHING;
                }

            case REMOVE_ROBOT:
                break;
        }
    }

    public void render(SpriteBatch batcher) {
        if (selectedRobot != null) {
            selectedRobot.render(batcher);
        }
        selectRobotPanel.render(batcher);
    }

    public void onMove(int x, int y) {
        if (action == PointerActions.ADD_ROBOT) {
            selectedRobot.setPosition(x, y);
        }
    }
}

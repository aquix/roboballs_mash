package com.mygdx.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.Configuration;
import com.mygdx.game_objects.Bullet;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.Robot;
import com.mygdx.game_objects.robots.GemBot;
import com.mygdx.game_objects.robots.GunnerBot;
import com.mygdx.gui_objects.HudPanel;
import com.mygdx.gui_objects.SelectRobotPanel;
import com.mygdx.lang_helpers.ArrayListHelpers;
import com.mygdx.lang_helpers.Predicate;
import com.mygdx.level_infrastructure.Level;
import com.mygdx.level_infrastructure.LevelFactory;

import java.util.*;

public class GameWorld {
    private TreeSet<Enemy> readyEnemies;
    private GameMap map;
    private SelectRobotPanel selectRobotPanel;
    private ArrayList<Class> availableRobots;
    private Robot selectedRobot;
    private int levelNumber;
    private PointerActions action;
    private Rectangle gameField;
    private Rectangle robotBarField;
    private Rectangle statusBarField;
    private float gameTime;
    private ArrayList<Bullet> bullets;
    private GameState gameState;
    private int gems;
    private int lives;
    private HudPanel hud;

    private ArrayListHelpers<Bullet> bulletArrayListHelpers;
    private ArrayListHelpers<Robot> robotArrayListHelpers;
    private ArrayListHelpers<Enemy> enemyArrayListHelpers;

    public GameWorld() {
        gameTime = 0;
        levelNumber = 1;
        Level level = LevelFactory.createLevel(levelNumber);

        // Initialize all level enemies
        readyEnemies = new TreeSet<Enemy>(new Comparator<Enemy>() {
            @Override
            public int compare(Enemy enemy, Enemy t1) {
                if (enemy.getSpawnTime() < t1.getSpawnTime()) {
                    return -1;
                } else if (enemy.getSpawnTime() > t1.getSpawnTime()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        readyEnemies.addAll(level.getEnemies());

        map = new GameMap(level.getLevelMap());
        availableRobots = new ArrayList<Class>(Arrays.asList(GemBot.class,
                GunnerBot.class));

        selectRobotPanel = new SelectRobotPanel(availableRobots);
        action = PointerActions.NOTHING;
        selectedRobot = null;

        statusBarField = new Rectangle(0, 0, Configuration.windowWidth, 100);
        gameField = new Rectangle(140, 110, 1000, 500);
        robotBarField = new Rectangle(0, Configuration.windowHeight - 100,
                Configuration.windowWidth, 100);

        bullets = new ArrayList<Bullet>();
        gameState = GameState.PLAY;
        gems = level.getStartGems();
        lives = 3;
        hud = new HudPanel(this.gems, this.lives);

        bulletArrayListHelpers = new ArrayListHelpers<Bullet>();
        enemyArrayListHelpers = new ArrayListHelpers<Enemy>();
        robotArrayListHelpers = new ArrayListHelpers<Robot>();
    }

    public void update(float delta) {
        gameTime += delta;

        // Create new enemies by spawn time
        if (readyEnemies.size() != 0 && gameTime >= readyEnemies.first()
                .getSpawnTime()) {
            Enemy newEnemy = readyEnemies.pollFirst();
            newEnemy.start();
            map.getEnemies().add(newEnemy);
        }

        // Update enemies state
        for (Enemy enemy : map.getEnemies()) {
            enemy.update(delta, map);
            for (Bullet bullet : bullets) {
                if (enemy.getRect().overlaps(bullet.getCollisionRect())) {
                    bullet.damageEnemy(enemy);
                }
            }
        }

        // Update map
        map.updateCells(map.getEnemies());

        // Grab new bullets from game map
        List<Bullet> newBullets = map.grabNewBullets();
        bullets.addAll(newBullets);
        newBullets.clear();

        // Update bullets and apply damage for enemies
        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }

        bulletArrayListHelpers.removeIf(bullets, new Predicate<Bullet>() {
            @Override
            public boolean test(Bullet obj) {
                return !obj.isActive();
            }
        });

        enemyArrayListHelpers.removeIf(map.getEnemies(), new Predicate<Enemy>() {
            @Override
            public boolean test(Enemy obj) {
                return !obj.isAlive();
            }
        });

        robotArrayListHelpers.removeIf(map.getRobots(), new Predicate<Robot>() {
            @Override
            public boolean test(Robot obj) {
                return !obj.isAlive();
            }
        });

        // Update robots
        for (Robot robot : map.getRobots()) {
            robot.update(delta, map);
        }

        if (readyEnemies.size() == 0 && map.getEnemies().size() == 0) {
            gameState = GameState.WIN;
        }

        // JAVA 8 code
//        //Delete death bullets
//        bullets.removeIf(new Predicate<Bullet>() {
//            @Override
//            public boolean test(Bullet bullet) {
//                return !bullet.isActive();
//            }
//        });
//
//        // Delete death enemies
//        map.getEnemies().removeIf(new Predicate<Enemy>() {
//            @Override
//            public boolean test(Enemy enemy) {
//                return !enemy.isAlive();
//            }
//        });
//
//        // Delete death robots
//        map.getRobots().removeIf(new Predicate<Robot>() {
//            @Override
//            public boolean test(Robot robot) {
//                return !robot.isAlive();
//            }
//        });
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
                break;
            case ADD_ROBOT:
                // If right mouse button was pressed then cancel robot planting
                if (button == Input.Buttons.RIGHT) {
                    selectedRobot = null;
                    action = PointerActions.NOTHING;
                } else if (button == Input.Buttons.LEFT) {
                    // If button was pressed on game field then plant robot
                    if (gameField.contains(x, y)) {
                        // If cell is empty and we can plant robot - do it
                        // else do nothing
                        if (map.plantRobot(selectedRobot, x, y)) {
                            map.getRobots().add(selectedRobot);
                            selectedRobot = null;
                            action = PointerActions.NOTHING;
                        }
                    }
                }
                break;
            case REMOVE_ROBOT:
                // TODO removing robots
                break;
        }
    }

    public void render(SpriteBatch batcher) {
        hud.render(batcher);
        if (selectedRobot != null) {
            selectedRobot.render(batcher);
        }
        selectRobotPanel.render(batcher);
        for (Robot robot : map.getRobots()) {
            robot.render(batcher);
        }

        for (Enemy enemy : map.getEnemies()) {
            enemy.render(batcher);
        }

        for (Bullet bullet : bullets) {
            bullet.render(batcher);
        }
    }

    public void onMove(int x, int y) {
        if (action == PointerActions.ADD_ROBOT) {
            selectedRobot.setPosition(x, y);
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}

package com.mygdx.gameworld;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.info.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.info.GameWorldInfo;
import com.mygdx.game_objects.bullets.Bullet;
import com.mygdx.game_objects.bullets.EnemyBullet;
import com.mygdx.game_objects.bullets.RobotBullet;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.collect_items.Gem;
import com.mygdx.game_objects.map.GameMap;
import com.mygdx.game_objects.robots.*;
import com.mygdx.game_objects.map.MapCell;
import com.mygdx.gui_objects.HudPanel;
import com.mygdx.gui_objects.RobotTile;
import com.mygdx.gui_objects.SelectRobotPanel;
import com.mygdx.lang_helpers.*;
import com.mygdx.level_infrastructure.Level;
import com.mygdx.level_infrastructure.LevelFactory;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class GameWorld implements Serializable {
    private TreeSet<Enemy> readyEnemies;
    private GameMap map;
    private SelectRobotPanel selectRobotPanel;
    private ArrayList<Class> availableRobots;
    private transient Robot selectedRobot;
    private transient RobotTile selectedRobotTile;
    private transient PointerActions action;
    private transient Rectangle gameField;
    private transient Rectangle robotBarField;
    private transient Rectangle statusBarField;
    private float gameTime;
    private ArrayList<RobotBullet> robotBullets;
    private ArrayList<EnemyBullet> enemyBullets;
    private GameState gameState;
    private int gemsCount;
    private int lives;
    private int levelNumber;
    private transient HudPanel hud;
    private transient ArrayList<Gem> gems;

    public GameWorld(int levelNumber) {
        this.levelNumber = levelNumber;
        gameTime = 0;
        Level level = LevelFactory.createLevel(levelNumber);

        // Initialize all level enemies
        readyEnemies = new TreeSet<Enemy>(new SerializableComparator<Enemy>() {
            @Override
            public int compare(Enemy enemy, Enemy t1) {
                if (enemy.getSpawnTime() < t1.getSpawnTime()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        readyEnemies.addAll(level.getEnemies());

        map = new GameMap(level.getLevelMap());
        availableRobots = new ArrayList<Class>(Arrays.asList(GemBot.class,
                GunnerBot.class, AirBot.class, ShieldBot.class, MineBot
                        .class, IceBot.class));

        selectRobotPanel = new SelectRobotPanel(availableRobots);
        action = PointerActions.NOTHING;
        selectedRobot = null;

        statusBarField = new Rectangle(0, 0, Configuration.windowWidth, 100);
        gameField = new Rectangle(140, 110, 1000, 500);
        robotBarField = new Rectangle(0, Configuration.windowHeight - 100,
                Configuration.windowWidth, 100);

        robotBullets = new ArrayList<RobotBullet>();
        enemyBullets = new ArrayList<EnemyBullet>();
        gameState = GameState.PLAY;
        gemsCount = level.getStartGems();
        lives = 3;
        hud = new HudPanel(this.lives, this.gemsCount, readyEnemies.last().getSpawnTime());
        gems = new ArrayList<Gem>();
    }

    public void update(float delta) {
        gameTime += delta;

        // Create new enemies by spawn time
        if (!readyEnemies.isEmpty() && gameTime >= readyEnemies.first()
                .getSpawnTime()) {
            Enemy newEnemy = readyEnemies.pollFirst();
            newEnemy.start();
            map.getEnemies().add(newEnemy);
        }

        // Update enemies state
        for (Enemy enemy : map.getEnemies()) {
            enemy.update(delta, map);
            for (RobotBullet bullet : robotBullets) {
                if (enemy.getCollisionRect().overlaps(bullet.getCollisionRect())) {
                    bullet.damageEnemy(enemy);
                }
            }

            if (enemy.getCollisionRect().x > Configuration.windowWidth - 140) {
                lives -= 1;
                enemy.kill();
            }
        }

        // Update map
        map.updateCells();

        // Grab new robotBullets from game map
        List<Bullet> newBullets = map.grabNewBullets();
        for (Bullet newBullet : newBullets) {
            if (newBullet instanceof RobotBullet) {
                robotBullets.add((RobotBullet)newBullet);
            } else if (newBullet instanceof EnemyBullet) {
                enemyBullets.add((EnemyBullet)newBullet);
            }
        }
        newBullets.clear();

        // Update robot bullets
        for (Bullet bullet : robotBullets) {
            bullet.update(delta, map);
        }

        // Update enemy bullets
        for (EnemyBullet bullet : enemyBullets) {
            bullet.update(delta, map);
        }

        // Grab new gemsCount
        List<Gem> newGems = map.grabNewGems();
        gems.addAll(newGems);
        newGems.clear();

        // Update gemsCount
        for (Gem gem : gems) {
            gem.update(delta);
        }

        DeadObjectsRemover.removeDead(robotBullets);
        DeadObjectsRemover.removeDead(enemyBullets);
        DeadObjectsRemover.removeDead(gems);
        DeadObjectsRemover.removeDead(map.getEnemies());
        DeadObjectsRemover.removeDead(map.getRobots());


        // Update robots
        for (Robot robot : map.getRobots()) {
            robot.update(delta, map);
            for (EnemyBullet bullet : enemyBullets) {
                if (robot.getCollisionRect().overlaps(bullet.getCollisionRect())) {
                    bullet.damageRobot(robot);
                }
            }
        }

        // Update hud
        hud.update(delta, gemsCount, lives);

        // Update robot tiles
        selectRobotPanel.update(delta, gemsCount);

        // Check for win
        if (readyEnemies.isEmpty() && map.getEnemies().isEmpty()) {
            gameState = GameState.WIN;
        }

        // Check for game over
        if (lives <= 0) {
            gameState = GameState.GAMEOVER;
        }

        // JAVA 8 code
//        //Delete death robotBullets
//        robotBullets.removeIf(new Predicate<Bullet>() {
//            @Override
//            public boolean test(Bullet bullet) {
//                return !bullet.isAlive();
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
                    gameState = GameState.PAUSE;
                } else if (gameField.contains(x, y)) {
                    // Check if gem picked
                    for (Gem gem : gems) {
                        if (gem.contains(x, y)) {
                            this.gemsCount += gem.pickGem();
                        }
                    }
                } else if (robotBarField.contains(x, y)) {
                    Robot newRobot = selectRobotPanel.getRobot(x, y, gemsCount);
                    if (newRobot == null) {
                        return;
                    }
                    action = PointerActions.ADD_ROBOT;
                    selectedRobot = newRobot;
                    selectedRobotTile = selectRobotPanel.getTile(x, y);
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
                            int gemsUsed = selectedRobotTile.robotUsed();
                            gemsCount -= gemsUsed;
                            selectedRobot = null;
                            selectedRobotTile = null;
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

    public void render(SpriteBatch batcher, float runTime) {
        batcher.disableBlending();
        batcher.draw(AssetLoader.getInstance().getLevelBackground(levelNumber),
                0, 0, Configuration.windowWidth, Configuration.windowHeight);
        batcher.enableBlending();

        hud.render(batcher, runTime);
        if (selectedRobot != null) {
            selectedRobot.render(batcher, 0);
        }
        selectRobotPanel.render(batcher, runTime);
        for (Robot robot : map.getRobots()) {
            robot.render(batcher, runTime);
        }

        for (Enemy enemy : map.getEnemies()) {
            enemy.render(batcher, runTime);
        }

        for (RobotBullet bullet : robotBullets) {
            bullet.render(batcher, runTime);
        }

        for (EnemyBullet bullet : enemyBullets) {
            bullet.render(batcher, runTime);
        }

        for (Gem gem : gems) {
            gem.render(batcher, runTime);
        }
    }

    public void render(ExtendedShapeRenderer shapeRenderer) {
        selectRobotPanel.render(shapeRenderer);

        if (action == PointerActions.ADD_ROBOT && selectedRobot != null) {
            for (MapCell[] cellLine : map.getCells()) {
                for (MapCell cell : cellLine) {
                    cell.render(shapeRenderer, cell.contains(selectedRobot.getX()
                            + 50, selectedRobot.getY() + 50), selectedRobot
                            .getCellTypes().contains(cell.getType()));
                }
            }
        }
    }

    public void onMove(int x, int y) {
        if (action == PointerActions.ADD_ROBOT) {
            selectedRobot.setPosition(x - 50, y - 50);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getLives() {
        return lives;
    }

    public void restoreState(GameWorldInfo info) {
        readyEnemies = info.readyEnemies;
        map = info.map;
        selectRobotPanel = info.selectRobotPanel;
        availableRobots = info.availableRobots;
        gameTime = info.gameTime;
        robotBullets = info.robotBullets;
        enemyBullets = info.enemyBullets;
        gameState = info.gameState;
        gemsCount = info.gemsCount;
        lives = info.lives;
        levelNumber = info.levelNumber;
        gameState = GameState.PLAY;
    }

    public GameWorldInfo getInfo() {
        GameWorldInfo info = new GameWorldInfo();
        info.readyEnemies = readyEnemies;
        info.map = map;
        info.selectRobotPanel = selectRobotPanel;
        info.availableRobots = availableRobots;
        info.gameTime = gameTime;
        info.robotBullets = robotBullets;
        info.enemyBullets = enemyBullets;
        info.gameState = gameState;
        info.gemsCount = gemsCount;
        info.lives = lives;
        info.levelNumber = levelNumber;
        return info;
    }
}

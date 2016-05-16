package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.mygdx.config.Configuration;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.RobotsData;
import java.util.HashMap;
import java.util.Map;


public class AssetLoader {
    // Singleton
    private static AssetLoader instance;

    private AssetLoader() { }

    public static AssetLoader getInstance() {
        if (instance == null) {
            instance = new AssetLoader();
        }
        return instance;
    }

    private HashMap<Integer, Texture> backgroundTextures;
    public HashMap<Integer, TextureRegion> backgrounds;

    private Texture robotTilesTexture;
    public HashMap<String, TextureRegion> robotTiles;
    private Texture robotsTexture;
    public HashMap<String, TextureRegion> robots;
    private Texture enemiesTexture;
    public HashMap<String, TextureRegion> enemies;
    public Animation simpleEnemyAnimation;

    private Texture bulletsTexture;
    public HashMap<String, TextureRegion> bullets;
    public TextureRegion littleGem;
    public TextureRegion bigGem;
    public TextureRegion heart;
    public TextureRegion gemIcon;

    private Texture mainMenuBackgroundTexture;
    public TextureRegion mainMenuBackground;

    private Texture menuIconsTexture;
    public TextureRegion levelTile;
    public TextureRegion lockedLevel;
    public TextureRegion toMenu;
    public TextureRegion nextLevel;
    public TextureRegion replayLevel;

    public void load() {
        backgroundTextures = new HashMap<Integer, Texture>();
        backgrounds = new HashMap<Integer, TextureRegion>();

        // Load textures from disk
        robotTilesTexture = new Texture(Gdx.files.internal("gui/robot_tiles.png"));
        robotsTexture = new Texture(Gdx.files.internal("robots/robots.png"));
        enemiesTexture = new Texture(Gdx.files.internal
                ("enemies/enemies.png"));
        bulletsTexture = new Texture(Gdx.files.internal("littles/littles.png"));
        mainMenuBackgroundTexture = new Texture(Gdx.files.internal
                ("backgrounds/main_menu_background_stub.png"));
        menuIconsTexture = new Texture(Gdx.files.internal
                ("gui/menu_icons.png"));

        // Load robot tiles
        robotTiles = new HashMap<String, TextureRegion>();
        robotTiles.put("GemBot", new TextureRegion(robotTilesTexture, 102, 0,
                102, 113));
        robotTiles.get("GemBot").flip(false, true);
        robotTiles.put("GunnerBot", new TextureRegion(robotTilesTexture, 0,
                0, 102, 113));
        robotTiles.get("GunnerBot").flip(false, true);

        // Load robots
        robots = new HashMap<String, TextureRegion>();
        robots.put("GemBot", new TextureRegion(robotsTexture, 0, 100, 100,
                100));
        robots.get("GemBot").flip(false, true);
        robots.put("GunnerBot", new TextureRegion(robotsTexture, 0, 0,
                RobotsData.getInstance().getData().get("GemBot").width,
                RobotsData.getInstance().getData().get("GemBot").height));
        robots.get("GunnerBot").flip(false, true);

        // Load enemies
        enemies = new HashMap<String, TextureRegion>();
        enemies.put("SimpleEnemy", new TextureRegion(enemiesTexture, 0, 0,
                EnemiesData.data.get("SimpleEnemy").width, EnemiesData.data
                .get("SimpleEnemy").height));
        enemies.get("SimpleEnemy").flip(false, true);

        // Load bullets
        bullets = new HashMap<String, TextureRegion>();
        bullets.put("GunnerBotBullet", new TextureRegion(bulletsTexture, 222,
            14, 50, 32));

        // Load main menu
        mainMenuBackground = new TextureRegion(mainMenuBackgroundTexture, 0,
                0, Configuration.windowWidth, Configuration.windowHeight);
        mainMenuBackground.flip(false, true);
        levelTile = new TextureRegion(menuIconsTexture, 0, 0, 75, 75);
        levelTile.flip(false, true);
        lockedLevel = new TextureRegion(menuIconsTexture, 75, 0, 75, 75);
        lockedLevel.flip(false, true);

        // Load winscreen icons
        toMenu = new TextureRegion(menuIconsTexture, 0, 84, 142, 142);
        toMenu.flip(false, true);
        nextLevel = new TextureRegion(menuIconsTexture, 142, 84, 142,
                142);
        nextLevel.flip(false, true);
        replayLevel = new TextureRegion(menuIconsTexture, 142 * 2, 84, 142,
                142);
        replayLevel.flip(false, true);

        // Load gems
        littleGem = new TextureRegion(bulletsTexture, 140, 0, 64, 64);
        littleGem.flip(false, true);
        bigGem = new TextureRegion(bulletsTexture, 63, 0, 71, 61);
        bigGem.flip(false, true);

        // Load hud icons
        heart = new TextureRegion(bulletsTexture, 0, 0, 56, 56);
        heart.flip(false, true);
        gemIcon = new TextureRegion(bulletsTexture, 63, 0, 71, 61);
        gemIcon.flip(false, true);

        // Load simple enemy frames
        int width = 198;
        int height = 150;
        TextureRegion[] simpleEnemyFrames = {new TextureRegion
                (enemiesTexture, 0, 0, width, height), new TextureRegion
                (enemiesTexture, width, 0, width, height), new TextureRegion
                (enemiesTexture, width * 2, 0, width, height), new TextureRegion
                (enemiesTexture, width * 3, 0, width, height)};

        for (TextureRegion frame : simpleEnemyFrames) {
            frame.flip(false, true);
        }

        simpleEnemyAnimation = new Animation(0.3f, simpleEnemyFrames);
        simpleEnemyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void dispose() {
        for (Integer key : backgroundTextures.keySet()) {
            backgroundTextures.get(key).dispose();
        }

        robotTilesTexture.dispose();
        robotsTexture.dispose();
        enemiesTexture.dispose();
    }

    public void disposeBackgroundsExcept(int levelNumber) {
        for (Integer key : backgroundTextures.keySet()) {
            if (key != levelNumber) {
                backgroundTextures.get(key).dispose();
                backgroundTextures.remove(key);
                backgrounds.remove(key);
            }
        }
    }

    public TextureRegion getLevelBackground(int levelNumber) {
        if (!backgroundTextures.containsKey(levelNumber) || !backgrounds
                .containsKey(levelNumber)) {
            backgroundTextures.put(levelNumber, new Texture(Gdx.files.internal
                    ("backgrounds/level" + levelNumber + ".png")));
            TextureRegion background = new TextureRegion(backgroundTextures.get
                    (levelNumber));
            background.flip(false, true);
            backgrounds.put(levelNumber, background);
        }
        return backgrounds.get(levelNumber);
    }
}

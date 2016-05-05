package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.config.Configuration;
import com.mygdx.config.EnemiesData;
import com.mygdx.config.RobotsData;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import sun.security.jca.GetInstance;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;


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

    private Texture backgroundTexture;
    public TextureRegion background;

    private Texture robotTilesTexture;
    public HashMap<String, TextureRegion> robotTiles;
    private Texture robotsTexture;
    public HashMap<String, TextureRegion> robots;
    private Texture enemiesTexture;
    public HashMap<String, TextureRegion> enemies;

    private Texture bulletsTexture;
    public HashMap<String, TextureRegion> bullets;
    public TextureRegion littleGem;
    public TextureRegion bigGem;

    private Texture mainMenuBackgroundTexture;
    public TextureRegion mainMenuBackground;

    private Texture menuIconsTexture;
    public TextureRegion levelTile;
    public TextureRegion lockedLevel;

    public void load() {
        // Load textures from disk
        backgroundTexture = new Texture(Gdx.files.internal
                ("backgrounds/background_stub.png"));
        robotTilesTexture = new Texture(Gdx.files.internal("gui/robot_tiles.png"));
        robotsTexture = new Texture(Gdx.files.internal("robots/robots_stub" +
                ".png"));
        enemiesTexture = new Texture(Gdx.files.internal("enemies/enemies_stub.png"));
        bulletsTexture = new Texture(Gdx.files.internal("bullets/bullets.png"));
        mainMenuBackgroundTexture = new Texture(Gdx.files.internal
                ("backgrounds/main_menu_background_stub.png"));
        menuIconsTexture = new Texture(Gdx.files.internal
                ("gui/menu_icons_stub.png"));

        // Load background
        background = new TextureRegion(backgroundTexture, 0, 0, 1280, 720);
        background.flip(false, true);

        // Load robot tiles
        robotTiles = new HashMap<String, TextureRegion>();
        robotTiles.put("GemBot", new TextureRegion(robotTilesTexture, 0, 0,
                75, 75));
        robotTiles.get("GemBot").flip(false, true);
        robotTiles.put("GunnerBot", new TextureRegion(robotTilesTexture, 0,
                75, 75, 75));
        robotTiles.get("GunnerBot").flip(false, true);

        // Load robots
        robots = new HashMap<String, TextureRegion>();
        robots.put("GemBot", new TextureRegion(robotsTexture, 0, 0,
                RobotsData.data.get("GemBot").width, RobotsData.data.get
                ("GemBot").height));
        robots.get("GemBot").flip(false, true);
        robots.put("GunnerBot", new TextureRegion(robotsTexture, 0, RobotsData.data.get
                ("GemBot").height, RobotsData.data.get("GemBot").width,
                RobotsData.data.get("GemBot").height));
        robots.get("GunnerBot").flip(false, true);

        // Load enemies
        enemies = new HashMap<String, TextureRegion>();
        enemies.put("SimpleEnemy", new TextureRegion(enemiesTexture, 0, 0,
                EnemiesData.data.get("SimpleEnemy").width, EnemiesData.data
                .get("SimpleEnemy").height));
        enemies.get("SimpleEnemy").flip(false, true);

        // Load bullets
        bullets = new HashMap<String, TextureRegion>();
        bullets.put("GunnerBotBullet", new TextureRegion(bulletsTexture, 0,
            0, 20, 20));

        // Load main menu
        mainMenuBackground = new TextureRegion(mainMenuBackgroundTexture, 0,
                0, Configuration.windowWidth, Configuration.windowHeight);
        levelTile = new TextureRegion(menuIconsTexture, 0, 0, 50, 50);
        lockedLevel = new TextureRegion(menuIconsTexture, 510, 0, 50, 50);

        // Load gems
        littleGem = new TextureRegion(bulletsTexture, 100, 100, 20, 20);
        bigGem = new TextureRegion(bulletsTexture, 100, 120, 30, 30);
    }

    public void dispose() {
        backgroundTexture.dispose();
        robotTilesTexture.dispose();
        robotsTexture.dispose();
        enemiesTexture.dispose();
    }
}

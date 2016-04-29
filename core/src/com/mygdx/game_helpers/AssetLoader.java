package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Texture robotsTexture;
    public HashMap<String, TextureRegion> robotTiles;
    public HashMap<String, TextureRegion> robots;

    public void load() {
        // Load textures from disk
        backgroundTexture = new Texture(Gdx.files.internal
                ("backgrounds/background_stub.png"));
        robotTilesTexture = new Texture((Gdx.files.internal("gui/robot_tiles.png")));
        robotsTexture = new Texture((Gdx.files.internal("robots/robots_stub" +
                ".png")));

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
                100, 100));
        robots.get("GemBot").flip(false, true);
        robots.put("GunnerBot", new TextureRegion(robotsTexture, 0, 100,
                100, 100));
        robots.get("GunnerBot").flip(false, true);

    }

    public void dispose() {
        backgroundTexture.dispose();
        robotTilesTexture.dispose();
    }
}

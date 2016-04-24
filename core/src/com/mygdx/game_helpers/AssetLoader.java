package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Vlad on 4/15/2016.
 */
public class AssetLoader {
    public static Texture backgroundTexture;
    public static TextureRegion background;

    public static void load() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/level1.png"));
        background = new TextureRegion(backgroundTexture, 0, 0, 800, 600);
        background.flip(false, true);
    }

    public static void dispose() {
        backgroundTexture.dispose();
    }
}

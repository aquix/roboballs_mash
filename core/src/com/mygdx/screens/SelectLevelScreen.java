package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Plane;
import com.mygdx.config.Configuration;
import com.mygdx.config.PlayerData;
import com.mygdx.game_helpers.AssetLoader;

public class SelectLevelScreen extends UniversalScreen {
    private int levelsCount;

    public SelectLevelScreen(Game game) {
        super(game);
        levelsCount = PlayerData.getInstance().getAvailableLevels();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.getInstance().mainMenuBackground, 0, 0, Configuration
                .windowWidth, Configuration.windowHeight);

        int columns = 5;
        int rows = (int)(Math.ceil((double)Configuration.levelsCount /
                columns));

        float startX = Configuration.windowWidth / 2 - (columns / 2.0f) * 75;
        float startY = Configuration.windowHeight / 2 - (rows/ 2.0f) * 75;
        float x = startX;
        float y = startY;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                batcher.draw(AssetLoader.getInstance().levelTile, x, y);
                x += 75;
            }
            y += 75;
            x = startX;
        }

        batcher.end();
    }
}

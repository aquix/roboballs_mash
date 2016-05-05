package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.Configuration;
import com.mygdx.config.PlayerData;
import com.mygdx.game_helpers.AssetLoader;

public class SelectLevelScreen extends UniversalScreen {
    private int unlockedLevelsCount;
    private Rectangle[] levelTiles;

    public SelectLevelScreen(Game game) {
        super(game);
        unlockedLevelsCount = PlayerData.getInstance().getAvailableLevels();
        levelTiles = new Rectangle[Configuration.levelsCount];

        // Create level tiles positions
        int columns = 5;
        int rows = (int)(Math.ceil((double)Configuration.levelsCount /
                columns));

        float startX = Configuration.windowWidth / 2 - (columns / 2.0f) * 75;
        float startY = Configuration.windowHeight / 2 - (rows/ 2.0f) * 75;
        float x = startX;
        float y = startY;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                levelTiles[i * columns + j] = new Rectangle(x, y, 50, 50);

                x += 75;
            }
            y += 75;
            x = startX;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.getInstance().mainMenuBackground, 0, 0, Configuration
                .windowWidth, Configuration.windowHeight);

        for (int i = 0; i < levelTiles.length; i++) {
            batcher.draw(AssetLoader.getInstance().levelTile, levelTiles[i].x,
                    levelTiles[i].y);
            if (i >= unlockedLevelsCount) {
                batcher.draw(AssetLoader.getInstance().lockedLevel, levelTiles[i].x,
                        levelTiles[i].y);
            }
        }

        batcher.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < levelTiles.length; i++) {
            if (levelTiles[i].contains(screenX, screenY) &&
                    i < unlockedLevelsCount) {
                game.setScreen(new GameScreen(game, i + 1));
            }
        }

        return true;
    }
}

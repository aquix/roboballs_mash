package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.config.Configuration;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;

public class GameScreen extends UniversalScreen {
    private GameWorld world;
    private GameRenderer renderer;

    private float scaleX;
    private float scaleY;

    private float runTime = 0;

    public GameScreen(Game game, int level) {
        super(game);
        world = new GameWorld(level);
        renderer = new GameRenderer(world, camera, batcher);

        this.scaleX = Configuration.windowWidth / (float)Gdx.graphics
                .getWidth();
        this.scaleY = Configuration.windowHeight / (float)Gdx.graphics.getHeight();
    }

    @Override
    public void render(float delta) {
        runTime += delta;

        world.update(delta);
        renderer.render(runTime);

        SpriteBatch batch;

        switch (world.getGameState()) {
            case WIN:
                // TODO fix win screen
                game.setScreen(new GameScreen(game, 1));
                break;
            case GAMEOVER:
                // TODO game over
                break;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        world.onClick((int)(screenX * scaleX), (int)(screenY * scaleY), button);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        world.onMove((int)(screenX * scaleX), (int)(screenY * scaleY));
        return true;
    }
}

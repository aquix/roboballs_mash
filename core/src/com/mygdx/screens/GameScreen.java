package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game_helpers.InputHandler;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameState;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.rb_mash.RoboballsMash;

import javax.script.ScriptEngine;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;

    private Game game;

    private float runTime = 0;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    public void setGame(Game game) {
        this.game = game;
    }



    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        runTime += delta;

        world.update(delta);
        renderer.render(runTime);

        SpriteBatch batch;

        switch (world.getGameState()) {
            case WIN:
                GameScreen winScreen = new GameScreen();
                game.setScreen(winScreen);
                winScreen.setGame(game);

                break;
            case GAMEOVER:
                // TODO game over
                break;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }
}

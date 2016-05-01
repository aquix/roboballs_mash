package com.mygdx.game_helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.config.Configuration;
import com.mygdx.gameworld.GameWorld;


public class InputHandler implements InputProcessor {
    private GameWorld world;
    private float scaleX;
    private float scaleY;

    public InputHandler(GameWorld world) {
        this.world = world;
        this.scaleX = Configuration.windowWidth / (float)Gdx.graphics
                .getWidth();
        this.scaleY = Configuration.windowHeight / (float)Gdx.graphics.getHeight();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        world.onClick((int)(screenX * scaleX), (int)(screenY * scaleY), button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        world.onMove((int)(screenX * scaleX), (int)(screenY * scaleY));
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

package com.mygdx.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_helpers.SaveManager;

public class MainMenuScreen extends UniversalScreen {
    private BitmapFont font;

    public MainMenuScreen(Game game) {
        super(game);
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
        AssetLoader.getInstance().menuMusic.play();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            if (SaveManager.isSavedLevelExists()) {
                game.setScreen(new LoadSaveScreen(game));
            } else {
                game.setScreen(new SelectLevelScreen(game));
                this.dispose();
            }
        }
        return true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.getInstance().mainMenuBackground, 0, 0, Configuration
                .windowWidth, Configuration.windowHeight);

        batcher.enableBlending();
        font.draw(batcher, "Touch to start game", Configuration.windowWidth /
                2 - 200, Configuration.windowHeight / 2 - 10);
        batcher.disableBlending();
        batcher.end();
    }

    @Override
    public void dispose() {
//        float currentVolume = AssetLoader.getInstance().menuMusic.getVolume();
//        while (currentVolume > 0) {
//            Gdx.app.log("Volume: ", String.valueOf(currentVolume));
//            currentVolume -= 0.01;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            AssetLoader.getInstance().menuMusic.setVolume(currentVolume);
//        }

        AssetLoader.getInstance().menuMusic.stop();
        super.dispose();
    }
}

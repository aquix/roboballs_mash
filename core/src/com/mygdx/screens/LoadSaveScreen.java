package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_helpers.SaveManager;
import com.mygdx.gui_objects.MenuAction;
import com.mygdx.gui_objects.MenuItem;

import java.util.ArrayList;

public class LoadSaveScreen extends UniversalScreen {
    private BitmapFont font;
    private ArrayList<MenuItem> menuItems;
    private GlyphLayout glyphLayout;

    public LoadSaveScreen(Game game) {
        super(game);

        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
        glyphLayout = new GlyphLayout();
        menuItems = new ArrayList<MenuItem>();
        initScreen();
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

        for (MenuItem item : menuItems) {
            item.render(batcher, 0);
        }

        batcher.disableBlending();
        batcher.end();
    }

    public void initScreen() {
        String text;
        menuItems.clear();

        text = "Load saved game?";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - glyphLayout.width / 2, Configuration.windowHeight / 2 - 50,
                glyphLayout.width, glyphLayout.height, font, text, false, MenuAction.NONE));

        text = "yes";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 270, Configuration.windowHeight / 2 + 50, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction
                .LOAD_SAVED));

        text = "no";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 + 230, Configuration.windowHeight / 2 + 50, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction
                .NOT_LOAD_SAVED));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (MenuItem item : menuItems) {
            if (item.contains(screenX, screenY) && item.isClickable()) {
                switch (item.getAction()) {
                    case NONE:
                        break;
                    case LOAD_SAVED:
                        GameScreen gameScreen = new GameScreen(game, 1);
                        game.setScreen(gameScreen);
                        gameScreen.loadGame();
                        break;
                    case NOT_LOAD_SAVED:
                        game.setScreen(new SelectLevelScreen(game));
                        break;
                }
            }
        }
        return true;
    }
}

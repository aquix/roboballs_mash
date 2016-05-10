package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_helpers.SaveManager;
import com.mygdx.gui_objects.MenuAction;
import com.mygdx.gui_objects.MenuItem;
import com.mygdx.lang_helpers.ArrayListHelpers;

import java.util.ArrayList;

public class PauseScreen extends UniversalScreen {
    private BitmapFont font;
    private UniversalScreen previousScreen;
    private boolean isSaveGameDialog;
    private MenuAction actionAfterSaveDialog;

    private ArrayList<MenuItem> menuItems;
    private GlyphLayout glyphLayout;

    public PauseScreen(Game game, UniversalScreen previousScreen) {
        super(game);
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
        this.previousScreen = previousScreen;
        isSaveGameDialog = false;
        menuItems = new ArrayList<MenuItem>();
        glyphLayout = new GlyphLayout();
        actionAfterSaveDialog = MenuAction.NONE;
        initPauseScreen();
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

        if (!isSaveGameDialog) {

        } else {

        }

        for (MenuItem item : menuItems) {
            item.render(batcher);
        }

        batcher.disableBlending();
        batcher.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (MenuItem item : menuItems) {
            if (item.contains(screenX, screenY) && item.isClickable()) {
                switch (item.getAction()) {
                    case NONE:
                        break;
                    case CONTINUE:
                        previousScreen.resume();
                        break;
                    case EXIT:
                    case EXIT_TO_MAIN:
                        actionAfterSaveDialog = item.getAction();
                        isSaveGameDialog = true;
                        initSaveGameDialog();
                        break;
                    case SAVE_YES:
                        if (previousScreen instanceof GameScreen) {
                            ((GameScreen) previousScreen).saveGame();
                        }
                        doAfterSaving();
                        break;
                    case SAVE_NO:
                        doAfterSaving();
                        break;
                }
            }
        }
        return true;
    }

    private void initPauseScreen() {
        String text;
        menuItems.clear();

        text = "PAUSE";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 90, Configuration.windowHeight / 2 - 150, glyphLayout
                .width, glyphLayout.height, font, text, false, MenuAction.NONE));

        text = "Continue";
        glyphLayout.setText(font, text);

        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 100, Configuration.windowHeight / 2 - 75, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction.CONTINUE));

        text = "Exit to main menu";
        glyphLayout.setText(font, text);

        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 150, Configuration.windowHeight / 2, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction.EXIT_TO_MAIN));

        text = "Exit";
        glyphLayout.setText(font, text);

        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 50, Configuration.windowHeight / 2 + 75, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction.EXIT));
    }

    private void initSaveGameDialog() {
        String text;
        menuItems.clear();

        text = "Save game?";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 90, Configuration.windowHeight / 2 - 50, glyphLayout
                .width, glyphLayout.height, font, text, false, MenuAction.NONE));

        text = "yes";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - 250, Configuration.windowHeight / 2 + 50, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction
                .SAVE_YES));

        text = "no";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 + 250, Configuration.windowHeight / 2 + 50, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction
                .SAVE_NO));
    }

    private void doAfterSaving() {
        if (actionAfterSaveDialog == MenuAction.EXIT) {
            game.dispose();
        } else if (actionAfterSaveDialog == MenuAction.EXIT_TO_MAIN) {
            game.setScreen(new MainMenuScreen(game));
        }
    }
}
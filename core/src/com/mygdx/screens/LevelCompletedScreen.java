package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.gui_objects.MenuAction;
import com.mygdx.gui_objects.MenuItem;

import java.util.ArrayList;

public class LevelCompletedScreen extends UniversalScreen {
    private BitmapFont font;
    private ArrayList<MenuItem> menuItems;
    private GlyphLayout glyphLayout;

    public LevelCompletedScreen(Game game) {
        super(game);
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
        menuItems = new ArrayList<MenuItem>();
        glyphLayout = new GlyphLayout();
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
        font.draw(batcher, "", Configuration
                .windowWidth / 2 - 200, Configuration.windowHeight / 2 - 10);
        batcher.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen(new SelectLevelScreen(game));
        return true;
    }

    private void initScreen() {
        String text;
        menuItems.clear();

        text = "Level completed!\nCongratulations";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - glyphLayout.width / 2, Configuration.windowHeight / 2 - 150,
                glyphLayout.width, glyphLayout.height, font, text, false, MenuAction.NONE));

        text = "";
        glyphLayout.setText(font, text);

        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - glyphLayout.width / 2, Configuration.windowHeight / 2 - 75, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction.CONTINUE));

        text = "";
        glyphLayout.setText(font, text);

        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - glyphLayout.width / 2, Configuration.windowHeight / 2, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction.EXIT_TO_MAIN));

        text = "";
        glyphLayout.setText(font, text);

        menuItems.add(new MenuItem(Configuration.windowWidth /
                2 - glyphLayout.width / 2, Configuration.windowHeight / 2 + 75, glyphLayout
                .width, glyphLayout.height, font, text, true, MenuAction.EXIT));
    }
}

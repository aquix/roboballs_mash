package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.info.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.gui_objects.MenuAction;
import com.mygdx.gui_objects.MenuItem;

import java.util.ArrayList;

public class GameOverScreen extends UniversalScreen {
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private ArrayList<MenuItem> menuItems;
    private int levelNumber;


    public GameOverScreen(Game game, int levelNumber) {
        super(game);
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
        glyphLayout = new GlyphLayout();
        menuItems = new ArrayList<MenuItem>();
        this.levelNumber = levelNumber;
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

        batcher.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (MenuItem item : menuItems) {
            if (item.contains(screenX, screenY) && item.isClickable()) {
                switch (item.getAction()) {
                    case NONE:
                        break;
                    case EXIT_TO_MAIN:
                        game.setScreen(new MainMenuScreen(game));
                        break;
                    case REPLAY_LEVEL:
                        game.setScreen(new GameScreen(game, levelNumber));
                        break;
                }
            }
        }
        return true;
    }

    private void initScreen() {
        String text;
        MenuItem menuItem;
        menuItems.clear();

        text = "Game over";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth / 2 -
                glyphLayout.width / 2, Configuration.windowHeight / 2 - 150,
                glyphLayout.width, glyphLayout.height, font, text, false, MenuAction.NONE));

        text = "";
        menuItem = new MenuItem(Configuration.windowWidth / 2 - 162,
                Configuration.windowHeight / 2, 142, 142, font,
                text, true, MenuAction.EXIT_TO_MAIN);
        menuItem.addImage(AssetLoader.getInstance().toMenu);
        menuItems.add(menuItem);

        text = "";
        menuItem = new MenuItem(Configuration.windowWidth / 2 + 20,
                Configuration.windowHeight / 2, 142, 142, font,
                text, true, MenuAction.REPLAY_LEVEL);
        menuItem.addImage(AssetLoader.getInstance().replayLevel);
        menuItems.add(menuItem);
    }
}

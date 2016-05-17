package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.config.CompletedLevel;
import com.mygdx.config.Configuration;
import com.mygdx.config.PlayerData;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.gui_objects.MenuAction;
import com.mygdx.gui_objects.MenuItem;

import java.util.ArrayList;

public class LevelCompletedScreen extends UniversalScreen {
    private BitmapFont font;
    private ArrayList<MenuItem> menuItems;
    private GlyphLayout glyphLayout;
    private int levelNumber;
    private CompletedLevel stars;

    public LevelCompletedScreen(Game game, int levelNumber, int livesLeft) {
        super(game);
        this.levelNumber = levelNumber;
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
        menuItems = new ArrayList<MenuItem>();
        glyphLayout = new GlyphLayout();
        initScreen();

        // Save user progress
        switch (livesLeft) {
            case 1:
                stars = CompletedLevel.ONE_STAR;
                break;
            case 2:
                stars = CompletedLevel.TWO_STARS;
                break;
            case 3:
                stars = CompletedLevel.THREE_STARS;
                break;
            default:
                stars = CompletedLevel.ONE_STAR;
                break;
        }

        PlayerData.getInstance().levelCompleted(levelNumber, stars);
        PlayerData.getInstance().savePlayerData();
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
                    case NEXT_LEVEL:
                        // TODO check if no available levels
                        game.setScreen(new GameScreen(game, levelNumber + 1));
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

        text = "Level completed!\nCongratulations";
        glyphLayout.setText(font, text);
        menuItems.add(new MenuItem(Configuration.windowWidth / 2 -
                glyphLayout.width / 2, Configuration.windowHeight / 2 - 150,
                glyphLayout.width, glyphLayout.height, font, text, false, MenuAction.NONE));

        text = "";
        menuItem = new MenuItem(Configuration.windowWidth / 2 - 223,
                Configuration.windowHeight / 2, 142, 142, font,
                text, true, MenuAction.EXIT_TO_MAIN);
        menuItem.addImage(AssetLoader.getInstance().toMenu);
        menuItems.add(menuItem);

        text = "";
        menuItem = new MenuItem(Configuration.windowWidth / 2 - 71,
                Configuration.windowHeight / 2, 142, 142, font,
                text, true, MenuAction.NEXT_LEVEL);
        menuItem.addImage(AssetLoader.getInstance().nextLevel);
        menuItems.add(menuItem);

        text = "";
        menuItem = new MenuItem(Configuration.windowWidth / 2 + 91,
                Configuration.windowHeight / 2, 142, 142, font,
                text, true, MenuAction.REPLAY_LEVEL);
        menuItem.addImage(AssetLoader.getInstance().replayLevel);
        menuItems.add(menuItem);
    }
}

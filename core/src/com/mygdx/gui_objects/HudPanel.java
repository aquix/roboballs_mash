package com.mygdx.gui_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;

import java.io.Serializable;

public class HudPanel extends GameObject {
    private int lives;
    private int gems;
    private BitmapFont font;
    private float lastWaveTime;
    private float fullTimeFillerWidth;


    public HudPanel(int lives, int gems, float lastWaveTime) {
        super(0, 20, 1280, 100);
        this.lives = lives;
        this.gems = gems;
        this.lastWaveTime = lastWaveTime;
        this.fullTimeFillerWidth = AssetLoader.getInstance().enemyTimeFiller
                .getRegionWidth();
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
    }

    public void render(SpriteBatch batcher, float runTime) {
        batcher.draw(AssetLoader.getInstance().gemIcon, rect.x + 900, rect.y);
        batcher.draw(AssetLoader.getInstance().heart, rect.x + 1030, rect.y);
        batcher.draw(AssetLoader.getInstance().enemyTimeScale, rect.x + 120,
                rect.y + 20);
        AssetLoader.getInstance().enemyTimeFiller.setRegionWidth
                ((int)(fullTimeFillerWidth * runTime / lastWaveTime));

        batcher.draw(AssetLoader.getInstance().enemyTimeFiller, rect.x + 122,
                rect.y + 28);
        font.draw(batcher, String.valueOf(gems), rect.x + 940, rect.y + 20);
        font.draw(batcher, String.valueOf(lives), rect.x + 1090, rect.y + 20);
    }

    public void update(float delta, int gems, int lives) {
        super.update(delta);
        this.gems = gems;
        this.lives = lives;
    }
}

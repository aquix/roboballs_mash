package com.mygdx.gui_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game_objects.GameObject;

import java.io.Serializable;

public class HudPanel extends GameObject {
    private int lives;
    private int gems;
    private BitmapFont font;

    public HudPanel(int lives, int gems) {
        super(1000, 20, 200, 100);
        this.lives = lives;
        this.gems = gems;
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt"), true);
    }

    @Override
    public void render(SpriteBatch batcher) {
        font.draw(batcher, String.valueOf(gems), rect.x + 20, rect.y + 10);
        font.draw(batcher, String.valueOf(lives), rect.x + 100, rect.y + 10);
    }

    public void update(float delta, int gems, int lives) {
        super.update(delta);
        this.gems = gems;
        this.lives = lives;
    }
}

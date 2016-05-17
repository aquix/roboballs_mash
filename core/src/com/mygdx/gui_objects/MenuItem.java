package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game_objects.GameObject;

public class MenuItem extends GameObject {
    private BitmapFont font;
    private String text;
    private boolean clickable;
    private MenuAction action;
    private TextureRegion image;

    public MenuItem(float x, float y, float width, float height, BitmapFont
            font, String text, boolean clickable, MenuAction action) {
        super(x, y, width, height);
        this.font = font;
        this.text = text;
        this.clickable = clickable;
        this.action = action;
    }

    @Override
    public void render(SpriteBatch batcher, float runTime) {
        if (image != null) {
            batcher.draw(image, rect.x, rect.y);
        }
        font.draw(batcher, text, rect.x, rect.y);
    }

    public boolean isClickable() {
        return clickable;
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    public MenuAction getAction() {
        return action;
    }

    public void addImage(TextureRegion image) {
        this.image = image;
    }
}

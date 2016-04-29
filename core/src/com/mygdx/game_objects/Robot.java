package com.mygdx.game_objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game_helpers.AssetLoader;

public class Robot extends GameObject {

    public Robot(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(AssetLoader.getInstance().robots.get(name), rect
                .x, rect.y, rect.width, rect.height);
    }
}

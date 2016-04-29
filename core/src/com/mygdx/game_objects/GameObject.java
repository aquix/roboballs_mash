package com.mygdx.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;


public abstract class GameObject implements IRenderable {
    protected Vector2 velocity;
    protected Vector2 position;
    protected Rectangle rect;
    protected String textureName;

    public GameObject(float x, float y, float width, float height) {
        this.rect = new Rectangle(x, y, width, height);
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
    }

    public float getWidth() {
        return rect.width;
    }

    public float getHeight() {
        return rect.height;
    }

    public float getX() {
        return rect.x;
    }

    public float getY() {
        return rect.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        rect.x = x;
        rect.y = y;
        position.x = x;
        position.y = y;
    }

    public boolean contains(float x, float y) {
        return rect.contains(x, y);
    }
}

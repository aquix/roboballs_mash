package com.mygdx.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;


public abstract class GameObject implements IRenderable {
    protected Vector2 velocity;
    protected Rectangle rect;
    protected String name;

    public GameObject(float x, float y, float width, float height) {
        this.rect = new Rectangle(x, y, width, height);
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

    public Rectangle getRect() {
        return rect;
    }

    public void setPosition(float x, float y) {
        rect.x = x;
        rect.y = y;
    }

    public boolean contains(float x, float y) {
        return rect.contains(x, y);
    }

    public void update(float delta) {
        rect.x += delta * velocity.x;
        rect.y += delta * velocity.y;
    }
}

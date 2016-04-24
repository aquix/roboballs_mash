package com.mygdx.game_objects;

import com.badlogic.gdx.math.Vector2;


public class GameObject {
    private Vector2 position;
    private Vector2 velocity;

    private int width;
    private int height;

    public GameObject(float x, float y, int width, int height) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}

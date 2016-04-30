package com.mygdx.game_objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashSet;

public class MapCell {
    private Rectangle rect;
    private Robot robot;
    private HashSet<Enemy> enemies;

    public MapCell(float x, float y) {
        rect = new Rectangle(x, y, 100, 100);
        robot = null;
        enemies = new HashSet<Enemy>();
    }

    public float getX() {
        return rect.x;
    }

    public float getY() {
        return rect.y;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    public HashSet<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isInCell(Rectangle rect) {
        return this.rect.overlaps(rect);
    }
 }

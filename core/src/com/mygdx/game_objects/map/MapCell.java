package com.mygdx.game_objects.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.IRenderable;
import com.mygdx.game_objects.Robot;

import java.io.Serializable;
import java.util.HashSet;

public class MapCell implements Serializable {
    private Rectangle rect;
    private Robot robot;
    private HashSet<Enemy> enemies;
    private CellType type;

    public MapCell(float x, float y, CellType type) {
        this.type = type;
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

    public CellType getType() {
        return type;
    }

    public void render(ShapeRenderer shapeRenderer, boolean selectedCell) {
        Color color;
        if (selectedCell) {
            if (robot == null) {
                color = new Color(0, 200, 0, 0.5f);
            } else {
                color = new Color(200, 0, 0, 0.5f);
            }
        } else {
            color = new Color(50, 50, 50, 0.5f);
        }
        shapeRenderer.setColor(color);
        shapeRenderer.rect(rect.x + 5, rect.y + 5, rect.width - 10, rect
                .height - 10);
    }

    public boolean contains(float x, float y) {
        return rect.contains(x, y);
    }
}

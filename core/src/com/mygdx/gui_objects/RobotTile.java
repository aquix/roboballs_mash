package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.config.RobotsData;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.Robot;
import com.mygdx.game_objects.RobotFactory;

public class RobotTile extends GameObject {
    private Class robotType;
    private int cost;
    private float cooldown;
    private float leftoverCooldown;

    public RobotTile(Class type, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.robotType = type;

        // Get only class name
        String[] typeNameParts = type.getName().split("\\.");
        name = typeNameParts[typeNameParts.length - 1];

        cooldown = RobotsData.getInstance().getData().get(name).tileCooldown;
        leftoverCooldown = cooldown;
        cost = RobotsData.getInstance().getData().get(name).cost;
    }

    public void render(SpriteBatch batcher){
        batcher.draw(AssetLoader.getInstance().robotTiles.get(name),
                rect.getX(), rect.getY());
    }

    public String getName() {
        return name;
    }

    public Class getRobotType() {
        return robotType;
    }

    @Override
    public void update(float delta) {
        leftoverCooldown -= delta;
        if (leftoverCooldown <= 0) {
            leftoverCooldown = 0;
        }
    }

    public Robot createNewRobot(int gems) {
        if (gems >= cost && leftoverCooldown <= 0) {
            return RobotFactory.createRobot(robotType, rect.x - 50, rect.y -
                    50);
        } else {
            return null;
        }
    }

    public int robotUsed() {
        leftoverCooldown = cooldown;
        return cost;
    }
}

package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.config.RobotsData;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.robots.Robot;
import com.mygdx.game_objects.robots.RobotFactory;
import com.mygdx.lang_helpers.ExtendedShapeRenderer;

public class RobotTile extends GameObject {
    private Class robotType;
    private int cost;
    private float cooldown;
    private float leftoverCooldown;
    private int playerGems;

    public RobotTile(Class type, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.robotType = type;

        // Get only class name
        String[] typeNameParts = type.getName().split("\\.");
        name = typeNameParts[typeNameParts.length - 1];

        cooldown = RobotsData.get(name).tileCooldown;
        leftoverCooldown = cooldown;
        cost = RobotsData.get(name).cost;
        playerGems = 0;
    }

    public void render(SpriteBatch batcher){
        batcher.draw(AssetLoader.getInstance().robotTiles.get(name),
                rect.getX(), rect.getY());
    }

    public void render(ExtendedShapeRenderer shapeRenderer) {
        if (playerGems < cost || leftoverCooldown > 0) {
            shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
            shapeRenderer.roundedRect(rect.x, rect.y, rect.width, rect
                    .height, 10);
        }

        if (leftoverCooldown > 0) {
            // Calculate percent of cooldown
            float percent = leftoverCooldown / cooldown;
            int cooldownY = (int)(rect.y + percent * rect.height);
            int cooldownHeight = (int)((1 - percent) * rect.height);

            shapeRenderer.setColor(new Color(255, 0, 0, 0.5f));
            shapeRenderer.roundedRect(rect.x, cooldownY, rect.width,
                    cooldownHeight, 10);
        }
    }

    public String getName() {
        return name;
    }

    public Class getRobotType() {
        return robotType;
    }

    public void update(float delta, int playerGems) {
        this.playerGems = playerGems;
        leftoverCooldown -= delta;
        if (leftoverCooldown <= 0) {
            leftoverCooldown = 0;
        }
    }

    public Robot createNewRobot(int gems) {
        if (gems >= cost && leftoverCooldown <= 0) {
            return RobotFactory.createRobot(robotType, rect.x, rect.y);
        } else {
            return null;
        }
    }

    public int robotUsed() {
        leftoverCooldown = cooldown;
        return cost;
    }
}

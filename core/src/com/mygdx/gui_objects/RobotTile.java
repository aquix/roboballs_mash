package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;

public class RobotTile extends GameObject {
    private Class robotType;
    private int cost;

    public RobotTile(Class type, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.robotType = type;

        // Get only class name
        String[] typeNameParts = type.getName().split("\\.");
        textureName = typeNameParts[typeNameParts.length - 1];
    }

    public void render(SpriteBatch batcher){
        batcher.draw(AssetLoader.getInstance().robotTiles.get(textureName),
                rect.getX(), rect.getY());
    }

    public String getName() {
        return textureName;
    }

    public Class getRobotType() {
        return robotType;
    }
}

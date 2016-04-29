package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        name = typeNameParts[typeNameParts.length - 1];
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
}

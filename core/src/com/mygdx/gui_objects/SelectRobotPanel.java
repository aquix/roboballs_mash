package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.Robot;
import com.mygdx.game_objects.RobotFactory;

import java.util.ArrayList;

public class SelectRobotPanel extends GameObject {
    private ArrayList<RobotTile> tiles;

    public SelectRobotPanel(float x, float y, int width, int height,
                            ArrayList<Class> robotTypes) {
        super(x, y, width, height);
        tiles = new ArrayList<RobotTile>();

        Vector2 newTilePosition = new Vector2(10, 620);
        float tileSize = 75;
        for (Class type : robotTypes) {
            tiles.add(new RobotTile(type, newTilePosition.x,
                    newTilePosition.y, tileSize, tileSize));

            newTilePosition.x += 85;
        }
    }

    public void render(SpriteBatch batcher) {
        Vector2 currentTilePosition = new Vector2(20, 620);

        for (RobotTile tile: tiles) {
            tile.render(batcher);
        }
    }

    public Robot getRobot(int x, int y) {
        for (RobotTile tile : tiles) {
            if (tile.contains(x, y)) {
                return RobotFactory.createRobot(tile.getRobotType(), x, y);
            }
        }

        return null;
    }

}

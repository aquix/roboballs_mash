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

    public SelectRobotPanel(ArrayList<Class> robotTypes) {
        super(0, 610, 110, 1280);
        tiles = new ArrayList<RobotTile>();

        Vector2 newTilePosition = new Vector2(10, 610);
        float tileSizeX = 102;
        float tileSizeY = 112;
        for (Class type : robotTypes) {
            tiles.add(new RobotTile(type, newTilePosition.x,
                    newTilePosition.y, tileSizeX, tileSizeY));

            newTilePosition.x += tileSizeX + 10;
        }
    }

    public void render(SpriteBatch batcher) {
        Vector2 currentTilePosition = new Vector2(20, 620);

        for (RobotTile tile: tiles) {
            tile.render(batcher);
        }
    }

    public Robot getRobot(int x, int y, int gems) {
        for (RobotTile tile : tiles) {
            if (tile.contains(x, y)) {
                return tile.createNewRobot(gems);
            }
        }

        return null;
    }

    @Override
    public void update(float delta) {
        for (RobotTile tile : tiles) {
            tile.update(delta);
        }
    }

    public RobotTile getTile(int x, int y) {
        for (RobotTile tile : tiles) {
            if (tile.contains(x, y)) {
                return tile;
            }
        }
        return null;
    }
}

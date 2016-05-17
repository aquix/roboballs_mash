package com.mygdx.gui_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.robots.Robot;
import com.mygdx.lang_helpers.ExtendedShapeRenderer;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectRobotPanel extends GameObject implements Serializable{
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

    public void render(SpriteBatch batcher, float runTime) {
        for (RobotTile tile: tiles) {
            tile.render(batcher, runTime);
        }
    }

    public void render(ExtendedShapeRenderer shapeRenderer) {
        for (RobotTile tile: tiles) {
            tile.render(shapeRenderer);
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

    public void update(float delta, int playerGems) {
        for (RobotTile tile : tiles) {
            tile.update(delta, playerGems);
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

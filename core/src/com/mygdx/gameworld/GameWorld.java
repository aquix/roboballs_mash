package com.mygdx.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game_objects.Enemy;
import com.mygdx.game_objects.Roboball;
import com.mygdx.rb_mash.RoboballsMash;

import java.util.ArrayList;

public class GameWorld {
    private ArrayList<Roboball> robots;
    private ArrayList<Enemy> enemies;

    public GameWorld() {
        robots = new ArrayList<Roboball>();
        enemies = new ArrayList<Enemy>();
    }

    public void update(float delta) {
    }

    public boolean addRobot(Roboball robot, int x_square, int y_square) {
        return false;
    }
}

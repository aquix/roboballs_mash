package com.mygdx.game_objects.enemies;

import com.mygdx.game_objects.map.GameMap;

import java.io.Serializable;

public interface IEnemyBehaviour extends Serializable {
    void update(float delta, GameMap map, Enemy self);
}

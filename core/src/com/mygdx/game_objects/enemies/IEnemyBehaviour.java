package com.mygdx.game_objects.enemies;

import com.mygdx.game_objects.map.GameMap;

public interface IEnemyBehaviour {
    void update(float delta, GameMap map, Enemy self);
}

package com.mygdx.game_objects.robots;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.config.RobotsData;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.bullets.GunnerBotBullet;
import com.mygdx.game_objects.enemies.Enemy;
import com.mygdx.game_objects.map.CellType;
import com.mygdx.game_objects.map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MineBot extends Robot implements IDamagable {
    private float damage;

    public MineBot(float x, float y) {
        super(x, y, RobotsData.get("MineBot").width,
                RobotsData.get("MineBot").height, "MineBot", RobotsData.get
                        ("MineBot").cooldown, RobotsData.get("MineBot").health,
                RobotsData.get("MineBot").cellTypes);
        damage = RobotsData.get("MineBot").damage;
    }

    @Override
    public void update(float delta, GameMap map) {
        super.update(delta, map);

        if (leftoverCooldown <= 0) {
            Vector2 cellPosition = map.getCellIndexes(rect.x + 10, rect.y + 10);
            Set<Enemy> enemiesInCell = map.getCells()[(int)cellPosition.y][
                    (int)cellPosition.x]
                    .getEnemies();
            if (!enemiesInCell.isEmpty()) {
                for (Enemy enemy : enemiesInCell) {
                    enemy.makeDamaged(this);
                    health = 0;
                }
            }
        }
    }

    @Override
    public float getDamage() {
        return damage;
    }
}

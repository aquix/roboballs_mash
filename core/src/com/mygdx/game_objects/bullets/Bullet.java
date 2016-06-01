package com.mygdx.game_objects.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.info.Configuration;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.IAliveable;
import com.mygdx.game_objects.IDamagable;
import com.mygdx.game_objects.map.GameMap;

public class Bullet extends GameObject implements IDamagable, IAliveable {
    protected boolean isActive;
    protected float damage;

    public Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);
        isActive = true;
    }

    @Override
    public void render(SpriteBatch batcher, float runTime) {
        batcher.draw(AssetLoader.getInstance().bullets.get(name), rect.x,
                rect.y, rect.width, rect.height);
    }

    public boolean isAlive() {
        return isActive;
    }

    public void update(float delta, GameMap map) {
        super.update(delta);
        if (!this.collisionRect.overlaps(Configuration.screenRect)) {
            this.isActive = false;
        }
    }

    public float getDamage() {
        return damage;
    }
}

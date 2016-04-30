package com.mygdx.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;

public class Bullet extends GameObject {
    protected boolean isActive;
    protected float damage;

    public Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);
        isActive = true;
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(AssetLoader.getInstance().bullets.get(name), rect.x,
                rect.y, rect.width, rect.height);
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (!this.collisionRect.overlaps(Configuration.screenRect)) {
            this.isActive = false;
        }
    }

    public float getDamage() {
        return damage;
    }

    public void damageEnemy(Enemy enemy) {
        boolean successDamage = enemy.makeDamaged(this);
        if (successDamage) {
            this.isActive = false;
        }
    }
}

package com.mygdx.game_objects.collect_items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game_helpers.AssetLoader;
import com.mygdx.game_objects.GameObject;
import com.mygdx.game_objects.IAliveable;

public class Gem extends GameObject implements IAliveable {
    private GemType type;
    private Vector2 appearPosition;
    private boolean isAlive;

    public Gem(float x, float y, GemType type) {
        super(x, y - 1, 64, 64);
        this.collisionRect = new Rectangle(x - 20, y - 20, 84, 84);

        this.appearPosition = new Vector2(x, y);
        this.acceleration.y = 400;
        this.velocity.y = -200;
        this.type = type;
        this.isAlive = true;
    }

    @Override
    public void update(float delta) {
        if (rect.y < appearPosition.y) {
            velocity.y = velocity.y + acceleration.y * delta;
        } else {
            velocity.y = 0;
        }
        super.update(delta);
    }

    @Override
    public void render(SpriteBatch batcher, float runTime) {
        if (type == GemType.LITTLE) {
            batcher.draw(AssetLoader.getInstance().littleGem, rect.x, rect.y);
        } else if (type == GemType.BIG) {
            batcher.draw(AssetLoader.getInstance().bigGem, rect.x, rect.y);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int pickGem() {
        AssetLoader.getInstance().gemSound.play();
        isAlive = false;
        return type.value;
    }
}

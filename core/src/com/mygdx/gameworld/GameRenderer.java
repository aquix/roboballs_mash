package com.mygdx.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.config.Configuration;
import com.mygdx.game_helpers.AssetLoader;

public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld world, OrthographicCamera camera,
                        SpriteBatch batcher) {
        this.world = world;
        this.camera = camera;
        this.batcher = batcher;
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.getInstance().background, 0, 0,
                Configuration.windowWidth, Configuration.windowHeight);
        world.render(batcher);
        batcher.end();
    }
}

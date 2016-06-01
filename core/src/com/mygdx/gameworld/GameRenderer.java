package com.mygdx.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.lang_helpers.ExtendedShapeRenderer;

public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera camera;

    private ExtendedShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    public GameRenderer(GameWorld world, OrthographicCamera camera,
                        SpriteBatch batcher, ExtendedShapeRenderer shapeRenderer) {
        this.world = world;
        this.camera = camera;
        this.batcher = batcher;
        this.shapeRenderer = shapeRenderer;
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        world.render(batcher, runTime);
        batcher.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        world.render(shapeRenderer);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}

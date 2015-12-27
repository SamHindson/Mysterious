package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

/**
 * Created by Sam on 26-Dec-15.
 */
public abstract class LevelElement {
    protected int x, y;
    protected Level level;

    protected ModelInstance model;

    public LevelElement(Level level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public abstract void update(float dt);
    public abstract void render(ModelBatch modelBatch, Environment environment);
}

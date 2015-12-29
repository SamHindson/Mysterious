package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sam on 26-Dec-15.
 *
 * An abstract class which all things level-related (walls, doors, buttons, ens.) will extend.
 */
public abstract class LevelElement {
    protected int x, y, z;
    protected Level level;

    protected ModelInstance model;

    public LevelElement(Level level, int x, int y) {
        this.level = level;
        this.x = x;
        this.z = y;
        
        this.y = 5;
    }

    public abstract void update(float dt);
    public abstract void render(ModelBatch modelBatch, Environment environment);

    public abstract boolean contains(float x, float y ,float z);

    /**
     * To check whether the player is able to walk through the object. Each element does its own thing in regards
     * to this. A door will be traversible when open, but not when closed. No Hogwarts magic here chaps.
     *
     * @return guess what
     */
    public abstract boolean isTraverisble();

    /**
     * Allows thingys to be activated (a torch will turn on, a door will open, you know.)
     */
    public abstract void activate();
}
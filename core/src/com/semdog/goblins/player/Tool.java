package com.semdog.goblins.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sam on 30-Dec-15.
 */
public abstract class Tool {
    protected Texture idleTexture, useTexture;
    protected Player holder;
    protected boolean using;

    protected Variant variant;

    public Tool(Player holder, Variant variant) {
        this.holder = holder;
        this.variant = variant;
    }

    public abstract String getName();
    public abstract int getDamage();

    public abstract void activate();
    public abstract void deactivate();

    public abstract void update(float dt);
    public abstract void render(SpriteBatch hudBatch);

    public enum Variant {
        STANDARD, ICE
    }

    public enum ToolType {
        SWORD
    }
}

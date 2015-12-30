package com.semdog.goblins.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.semdog.goblins.graphics.TextureMaster;

/**
 * Created by Sam on 30-Dec-15.
 */
public class Sword extends Tool {

    public Sword(Player holder, Tool.Variant variant) {
        super(holder, variant);
    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public int getDamage() {
        return 75;
    }

    @Override
    public void activate() {
        using = true;
    }

    @Override
    public void deactivate() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch hudBatch) {
        hudBatch.draw(TextureMaster.get("genericsword"), Gdx.graphics.getWidth() / 2, 0, 200, 200);
    }
}

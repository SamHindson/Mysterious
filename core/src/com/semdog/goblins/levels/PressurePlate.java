package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.player.Player;

/**
 * Created by Sam on 31-Dec-15.
 *
 * A button which goes boom when the player stands on it
 */
public class PressurePlate extends LevelElement {

    private short aid;
    private boolean activated = false;

    private static Model baseModel;

    static {
        baseModel = new ModelBuilder().createBox(5, 1, 5,
                new Material(ColorAttribute.createAmbient(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    public PressurePlate(Level level, int x, int y, int z, short aid) {
        super(level, x, y, z);

        this.aid = aid;

        model = new ModelInstance(baseModel);
        model.transform.setTranslation(x * 10, y * 10 - 10, z * 10);
    }

    @Override
    public void update(Level level, float dt) {

    }

    @Override
    public void render(DecalBatch decalBatch, ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model, environment);
    }

    @Override
    public boolean isTraverisble() {
        return true;
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public void touchedByPlayer(Player player) {
        if (!activated) {
            level.activateBlock(player, aid);
            activated = true;
        }
    }
}

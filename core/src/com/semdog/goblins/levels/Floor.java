package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.player.Player;

/**
 * Created by Sam on 30-Dec-15.
 */
public class Floor extends LevelElement {
    private static Model baseModel;

    static {
        baseModel = new ModelBuilder().createBox(10, 2, 10,
                new Material(TextureAttribute.createDiffuse(TextureMaster.get("floor"))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }

    public Floor(Level level, int x, int y, int z) {
        super(level, x, y, z);

        model = new ModelInstance(baseModel);
        model.transform.setToTranslation(x * 10, y * 10 - 1, z * 10);
    }

    @Override
    public void update(Level level, float dt) {

    }

    @Override
    public void render(DecalBatch decalBatch, ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model, environment);
    }

    @Override
    public boolean contains(float x, float y, float z) {
        return false;
    }

    @Override
    public boolean isTraverisble() {
        return false;
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public void touchedByPlayer(Player player) {

    }
}

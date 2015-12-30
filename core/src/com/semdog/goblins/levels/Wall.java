package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.player.Player;

/**
 * Created by Sam on 26-Dec-15.
 *
 * Guess.
 */
public class Wall extends LevelElement {

    private static Model baseModel;

    static {
        baseModel = new ModelBuilder().createBox(10, 10, 10,
                new Material(TextureAttribute.createDiffuse(TextureMaster.get("wall1"))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }

    public Wall(Level level, int x, int y, int z) {
        super(level, x, y, z);

        model = new ModelInstance(baseModel);
        model.transform.setToTranslation(x * 10, y * 10 - 5, z * 10);
    }

    @Override
    public boolean isTraverisble() {
        return false;
    }

    @Override
    public void update(Level level, float dt) {

    }

    @Override
    public void activate(Player player) {}

    @Override
    public void touchedByPlayer(Player player) {}

    @Override
    public void render(DecalBatch decalBatch, ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model, environment);
    }
}

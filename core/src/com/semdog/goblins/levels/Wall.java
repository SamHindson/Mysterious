package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.semdog.goblins.graphics.TextureMaster;

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

    public Wall(Level level, int x, int y) {
        super(level, x, y);

        model = new ModelInstance(baseModel);
        model.transform.setToTranslation(x * 10, 5, y * 10);
    }

    @Override
    public boolean isTraverisble() {
        return false;
    }

    /**
     * A really super lazy implementation of collision detection, my god.
     * @param x
     * @param y
     * @param z
     * @return
     */
    @Override
    public boolean contains(float x, float y, float z) {
        return Vector2.dst(x, z, this.x * 10, this.z * 10) < 5;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void activate() {}

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model);
    }
}

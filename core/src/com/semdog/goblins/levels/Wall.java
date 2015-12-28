package com.semdog.goblins.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.semdog.goblins.graphics.TextureMaster;

/**
 * Created by Sam on 26-Dec-15.
 */
public class Wall extends LevelElement {

    private static Model baseModel;

    static {
        System.out.println("Made Wall Model");
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
    public void beginPlayerContact() {

    }

    @Override
    public boolean isTraverisble() {
        return false;
    }

    @Override
    public boolean contains(float x, float y, float z) {
        //System.out.println("The point is at " + point);
        //System.out.println("I am at (" + (x * 10) + ", " + (y) + ", " + (z * 10) + ")");
        return Vector2.dst(x, z, this.x * 10, this.z * 10) < 5;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void activate() {
        System.out.println("Yee");
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model, environment);
    }
}

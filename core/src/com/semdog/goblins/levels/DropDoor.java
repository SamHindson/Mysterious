package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.semdog.goblins.graphics.TextureMaster;

/**
 * Created by Sam on 28-Dec-15.
 *
 * This is a door that drops when the player activates it.
 */
public class DropDoor extends LevelElement {

    private boolean dropping = false;
    private boolean dropped = false;

    private float eh = 5;

    private static Model baseModel;

    static {
        baseModel = new ModelBuilder().createBox(10, 10, 10,
                new Material(TextureAttribute.createDiffuse(TextureMaster.get("dropdoor"))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }

    public DropDoor(Level level, int x, int y) {
        super(level, x, y);
        System.out.println("New Drop Door!");
        model = new ModelInstance(baseModel);
        model.transform.setToTranslation(x * 10, 5, y * 10);
    }

    @Override
    public void update(float dt) {
        if(dropping) {
            eh -= dt * 10;
            System.out.println("Going down!");
            System.out.println(eh);
            model.transform.setToTranslation(x * 10, eh, z * 10);

            if(eh <= -5) {
                eh = -4.999f;
                y = 0;
                dropping = false;
                dropped = true;
            }
        }
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model, environment);
    }

    @Override
    public boolean contains(float x, float y, float z) {
        return Vector2.dst(x, z, this.x * 10, this.z * 10) < 5;
    }

    @Override
    public void beginPlayerContact() {

    }

    @Override
    public boolean isTraverisble() {
        return dropped;
    }

    @Override
    public void activate() {
        if(!dropped && !dropping) {
            dropping = true;
        }
    }
}

package com.semdog.goblins.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.semdog.goblins.graphics.TextureMaster;

/**
 * Created by Sam on 26-Dec-15.
 */
public class Level {

    private LevelElement[] elements;
    private int width, height;

    public int sx, sy;

    private ModelInstance floor;

    public Level(Environment environment) {
        Gdx.app.log("Level", "I mean... Yeah");

        elements = new LevelElement[900];

        width = 30;
        height = 30;

        int u = 0;

        boolean found = false;

        sx = sy = 20;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    elements[u] = new Wall(this, x, y);
                } else if (MathUtils.randomBoolean(0.1f)) {
                    elements[u] = new DropDoor(this, x, y);
                } /*else {
                    elements[u] = new Wall(this, x, y);
                }*/

                u++;
            }
        }

        Model floorModel = new ModelBuilder().createBox(300, 1, 300, new Material(TextureAttribute.createDiffuse(TextureMaster.get("floor"))), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        floor = new ModelInstance(floorModel);
        floor.transform.setToTranslation(150, -1, 150);
    }

    public Vector2 getSpawn() {
        return new Vector2(sx, sy);
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        for (LevelElement element : elements) {
            if (element != null) {
                element.render(modelBatch, environment);
            }
        }

        modelBatch.render(floor, environment);
    }

    public void update(float dt) {
        for (LevelElement element : elements) {
            if (element != null) {
                element.update(dt);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFree(float x, float y, float z) {
        for (LevelElement element : elements) {
            if (element != null) {
                if (element.contains(x, y, z)) {
                    element.beginPlayerContact();
                    return element.isTraverisble();
                }
            }
        }

        return true;
    }

    public void playerActivated(float x, float y, float z) {
        for (LevelElement element : elements) {
            if (element != null) {
                if (element.contains(x, y, z)) {
                    element.activate();
                }
            }
        }
    }
}

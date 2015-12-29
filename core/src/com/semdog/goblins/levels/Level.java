package com.semdog.goblins.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.semdog.goblins.graphics.TextureMaster;

/**
 * Created by Sam on 26-Dec-15.
 *
 * The level class. In the foreseeable future it'll work on loading schematics and not just randomly generated crap
 */
public class Level {

    private Array<LevelElement> elements;
    private int width, length, height;

    public int sx, sz;

    private ModelInstance floor;

    public Level(Environment environment) {
        height = 3;

        Pixmap[] levels = new Pixmap[3];

        levels[0] = new Pixmap(Gdx.files.internal("data/one1.png"));
        levels[1] = new Pixmap(Gdx.files.internal("data/one2.png"));
        levels[2] = new Pixmap(Gdx.files.internal("data/one3.png"));

        width = 7;
        length = 12;

        elements = new Array<>();

        for(int y = 0; y < height; y++) {
            for (int z = 0; z < length; z++) {
                for(int x = 0; x < width; x++) {

                    //System.out.print(Integer.toHexString(levels[y].getPixel(x, z)) + " ");

                    if(levels[y].getPixel(x, z) == 0xffFFFFFF) {
                        /*System.out.println("Heh");
                        sx = 4;
                        sz = 6;*/
                        System.out.print('-');
                    } else if(levels[y].getPixel(x, z) == 0xff) {
                        elements.add(new Wall(this, x, y, z));
                        System.out.print('0');
                    } else if(levels[y].getPixel(x, z) == 0xff0000FF) {
                        sx = x;
                        sz = z;
                        System.out.print('x');
                    } else {
                        elements.add(new DropDoor(this, x, y, z));
                        System.out.print('?');
                    }

                    System.out.print(' ');

                }

                System.out.println();
            }
        }

        System.out.println(sx + ", " + sz);

        Model floorModel = new ModelBuilder().createBox(width * 10, 1, length * 10, new Material(TextureAttribute.createDiffuse(TextureMaster.get("floor"))), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        floor = new ModelInstance(floorModel);
        floor.transform.setToTranslation(150, -1, 150);
    }

    public Vector2 getSpawn() {
        return new Vector2(sx, sz);
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        for (LevelElement element : elements) {
            if (element != null) {
                element.render(modelBatch, environment);
            }
        }

        //modelBatch.render(floor, environment);
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
                    return element.isTraverisble();
                }
            }
        }

        return true;
    }
    //blake was here
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

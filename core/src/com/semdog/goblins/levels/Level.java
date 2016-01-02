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
import com.semdog.goblins.player.Player;

import java.util.HashMap;

/**
 * Created by Sam on 26-Dec-15.
 * <p>
 * The level class. In the foreseeable future it'll work on loading schematics and not just randomly generated crap
 */
public class Level {

    private Array<LevelElement> elements;
    private Array<Activatible> activatibles;
    private int width, length, height;

    private int sx, sz;

    private float age;

    private ModelInstance floor;

    private static final String[] blockTypes = {
            "Nothing", "Wall", "Door", "Lamp", "Button", "Floor", "Player Spawn", "Level Changer", "Enemy Spawn", "Button", "Obstacle", "Chest", "Button"
    };

    public Level(Environment environment) {
        height = 4;

        Pixmap[] levels = new Pixmap[4];

        levels[0] = new Pixmap(Gdx.files.internal("data/one0.png"));
        levels[1] = new Pixmap(Gdx.files.internal("data/one1.png"));
        levels[2] = new Pixmap(Gdx.files.internal("data/one2.png"));
        levels[3] = new Pixmap(Gdx.files.internal("data/one3.png"));

        width = 20;
        length = 20;

        elements = new Array<>();
        activatibles = new Array<>();

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < length; z++) {
                for (int x = 0; x < width; x++) {

                    int color = levels[y].getPixel(x, z);
                    String hex = Integer.toHexString(color);
                    System.out.println("Pixel: " + color);
                    System.out.println("Hex: " + hex);

                    int t = (color & 0xf00000) >> 20;
                    System.out.println("Block Type: " + t);

                    int[] miscInfo = new int[5];

                    miscInfo[0] = (color & 0x0f0000) >> 16;
                    miscInfo[1] = (color & 0x00f000) >> 12;
                    miscInfo[2] = (color & 0x000f00) >> 8;
                    miscInfo[3] = (color & 0x0000f0) >> 4;
                    miscInfo[4] = (color & 0x00000f);

                    System.out.println();

                    switch (t) {
                        case 1:
                            elements.add(new Wall(this, x, y, z, miscInfo[0]));
                            break;
                        case 2:
                            DropDoor door = new DropDoor(this, x, y, z, miscInfo[1]);
                            activatibles.add(door);
                            elements.add(door);
                            break;
                        case 3:
                            System.out.println("Lamps... Eh");
                            break;
                        case 4:
                            elements.add(new PressurePlate(this, x, y, z, (short)miscInfo[0]));
                            break;
                        case 5:
                            elements.add(new Floor(this, x, y, z));
                            break;
                        case 6:
                            sx = x;
                            sz = z;
                            break;
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println(sx + ", " + sz);

        Model floorModel = new ModelBuilder().createBox(width * 10, 1, length * 10, new Material(TextureAttribute.createDiffuse(TextureMaster.get("floor2"))), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        floor = new ModelInstance(floorModel);
        floor.transform.setToTranslation(150, -1, 150);
    }

    public Vector2 getSpawn() {
        return new Vector2(sx, sz);
    }

    public void render(DecalBatch decalBatch, ModelBatch modelBatch, Environment environment) {
        for (LevelElement element : elements) {
            if (element != null) {
                element.render(decalBatch, modelBatch, environment);
            }
        }

        //modelBatch.render(floor, environment);
    }

    public void update(float dt) {
        age += dt;
        for (LevelElement element : elements) {
            if (element != null) {
                element.update(this, dt);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFree(Player player, float x, float y, float z) {
        for (LevelElement element : elements) {
            if (element != null) {
                if (element.contains(x, y, z)) {
                    element.touchedByPlayer(player);
                    return element.isTraverisble();
                }
            }
        }

        return true;
    }

    //blake was here
    public void playerActivated(Player player, float x, float y, float z) {
        for (LevelElement element : elements) {
            if (element != null) {
                if (element.contains(x, y, z)) {
                    element.activate(player);
                }
            }
        }
    }

    public float getAge() {
        return age;
    }

    public void playerIsAt(float x, float y, float z) {

    }

    public void activateBlock(Player player, int a) {
        System.out.println("Activating activatible #" + a);

        for(Activatible activatible : activatibles) {
            if(activatible.getID() == a) {
                activatible.activate(player);
            }
        }
    }
}

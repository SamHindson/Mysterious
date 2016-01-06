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
import com.semdog.goblins.datamanagement.SLevelLoader;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.player.Player;
import main.SLevel;

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

    public static final int NOTHING = 0;//        shift bits        normal b|v|d
    public static final int WALL = 0x1;//            (8)   nomral
    public static final int DOOR = 0x2;//            (16)  b|v|d|id
    public static final int LAMPS = 0x3;//           (16)  b|v|d|id
    public static final int PICKUP = 0x4;//          (8)   normal
    public static final int FLOOR = 0x5;//           (8)   normal
    public static final int PLAYERSPAWN = 0x6; //    (0)   b
    public static final int ENEMYSPAWN = 0x7;//      (4)   normal
    public static final int DECALS = 0x8;//          (8)   normal
    public static final int OBSTACLES = 0x9;//       (8)   normal
    public static final int BOSSSPAWN = 0xA;//       (4)   b|type
    public static final int BUTTON = 0xB;//          (16)  b|v|d|id
    public static final int LEVELCHANGER = 0xC;//    (8)   b|level

    public Level(Environment environment) {
        height = 4;

        SLevel k = SLevelLoader.load("doogies");

        width = k.getSchems()[0].length;
        length = k.getSchems()[0][0].length;

        elements = new Array<>();
        activatibles = new Array<>();

        for (int y = 0; y < height; y++) {
            for (int z = 0; z < length; z++) {
                for (int x = 0; x < width; x++) {

                    int color = k.getSchems()[y][z][x];
                    String hex = Integer.toHexString(color);
                    //System.out.println("Pixel: " + color);
                    //System.out.println("Hex: " + hex);

                    int t = (color & 0xf0000) >> 16;
                    //System.out.println("Block Type: " + t);

                    int[] miscInfo = new int[5];

                    miscInfo[0] = (color & 0x0f000) >> 12;
                    miscInfo[1] = (color & 0x00f00) >> 8;
                    miscInfo[2] = (color & 0x000f0) >> 4;
                    miscInfo[3] = (color & 0x0000f);

                    System.out.println();

                    switch (t) {
                        case WALL:
                            elements.add(new Wall(this, x, y, z, miscInfo[0]));
                            break;
                        case DOOR:
                            DropDoor door = new DropDoor(this, x, y, z, miscInfo[2]);
                            activatibles.add(door);
                            elements.add(door);
                            break;
                        case LAMPS:

                            break;
                        case PICKUP:

                            break;
                        case FLOOR:
                            elements.add(new Floor(this, x, y, z));
                            break;
                        case PLAYERSPAWN:
                            sx = x;
                            sz = z;
                            break;
                        case ENEMYSPAWN:

                            break;
                        case DECALS:

                            break;
                        case OBSTACLES:

                            break;
                        case BOSSSPAWN:

                            break;
                        case BUTTON:
                            elements.add(new PressurePlate(this, x, y, z, (short)miscInfo[2]));
                            break;
                        case LEVELCHANGER:

                            break;
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println(sx + ", " + sz);

        Model floorModel = new ModelBuilder().createBox(width * 10, 1, length * 10, new Material(TextureAttribute.createDiffuse(TextureMaster.get("floor2"))), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
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

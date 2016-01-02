package com.semdog.goblins.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Sam on 26-Dec-15.
 * <p>
 * A class that loads all the needed textures at runtime and helps them to be easily accessible.
 */
public class TextureMaster {
    public static boolean loading = true;

    private static Texture spriteSheet, toolSheet;
    private static HashMap<String, TextureRegion> textures;

    public static void init() {
        spriteSheet = new Texture(Gdx.files.internal("assets/sprites2.png"));
        toolSheet = new Texture(Gdx.files.internal("assets/tools.png"));

        textures = new HashMap<>();

        new Thread(() -> {
            loadTexture(spriteSheet, "wall1", 0, 0, 16, 16);
            loadTexture(spriteSheet, "wall2", 1, 0, 16, 16);
            loadTexture(spriteSheet, "wall3", 2, 0, 16, 16);
            loadTexture(spriteSheet, "wall4", 3, 0, 16, 16);
            loadTexture(spriteSheet, "wall5", 4, 0, 16, 16);
            loadTexture(spriteSheet, "wall6", 5, 0, 16, 16);
            loadTexture(spriteSheet, "wall7", 6, 0, 16, 16);
            loadTexture(spriteSheet, "wall8", 7, 0, 16, 16);
            loadTexture(spriteSheet, "floor1", 0, 1, 16, 16);
            loadTexture(spriteSheet, "floor2", 0, 1, 16, 16);
            loadTexture(spriteSheet, "floor3", 1, 1, 16, 16);
            loadTexture(spriteSheet, "pressureplate", 0, 2, 16, 16);
            loadTexture(spriteSheet, "sworddecal", 0, 3, 16, 16);

            loadTexture(toolSheet, "genericsword", 0, 0, 50, 50);
            loading = false;
        }).start();
    }

    private static synchronized void loadTexture(Texture sheet, String id, int x, int y, int w, int h) {
        TextureRegion region = new TextureRegion(sheet, x * w, y * w, w, h);
        textures.put(id, region);
    }

    public static TextureRegion get(String id) {
        return textures.get(id);
    }
}

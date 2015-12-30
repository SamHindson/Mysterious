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
        spriteSheet = new Texture(Gdx.files.internal("assets/sprites.png"));
        toolSheet = new Texture(Gdx.files.internal("assets/tools.png"));

        textures = new HashMap<>();

        new Thread(() -> {
            loadTexture(spriteSheet, "wall1", 0, 0, 12, 12);
            loadTexture(spriteSheet, "floor", 1, 0, 12, 12);
            loadTexture(spriteSheet, "dropdoor", 2, 0, 12, 12);
            loadTexture(spriteSheet, "sworddecal", 3, 0, 12, 12);
            loadTexture(spriteSheet, "toolslot", 4, 0, 12, 12);

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

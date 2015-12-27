package com.semdog.goblins.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Sam on 26-Dec-15.
 */
public class TextureMaster {
    public static boolean loading = true;

    private static Texture spriteSheet;
    private static HashMap<String, TextureRegion> textures;

    private static int loadNumber = 0;

    public static void init() {
        spriteSheet = new Texture(Gdx.files.internal("sprites.png"));

        textures = new HashMap<String, TextureRegion>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadTexture("floor1");
                loadTexture("wall1");
                loading = false;
                Gdx.app.log("TextureMaster", "Loaded all of it!");
            }
        }).start();
    }

    private static synchronized void loadTexture(String id) {
        int x = loadNumber % 10;
        int y = loadNumber / 10;
        Gdx.app.log("KEK", x + " - " + y);
        TextureRegion region = new TextureRegion(spriteSheet, x * 10, y * 10, 10, 10);
        textures.put(id, region);
        loadNumber++;
    }

    public static TextureRegion get(String id) {
        return textures.get(id);
    }
}

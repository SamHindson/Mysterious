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
                loadTexture("wall1");
                loadTexture("floor");
                loadTexture("dropdoor");
                loading = false;
                Gdx.app.log("TextureMaster", "Loaded all of it!");
            }
        }).start();
    }

    private static synchronized void loadTexture(String id) {
        int x = loadNumber % 12;
        int y = loadNumber / 12;
        Gdx.app.log("KEK", x + " - " + y);
        TextureRegion region = new TextureRegion(spriteSheet, x * 12, y * 12, 12, 12);
        textures.put(id, region);
        loadNumber++;
    }

    public static TextureRegion get(String id) {
        return textures.get(id);
    }
}

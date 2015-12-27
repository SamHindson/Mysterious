package com.semdog.goblins.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Sam on 26-Dec-15.
 */
public class Level {

    private LevelElement[] elements;
    private int width, height;

    public int sx, sy;

    public Level(Environment environment) {
        Gdx.app.log("Level", "I mean... Yeah");

        elements = new LevelElement[900];

        width = 30;
        height = 30;

        int u = 0;

        boolean found = false;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    elements[u] = new Wall(this, x, y);
                } else if (MathUtils.randomBoolean(0.9f)) {
                    elements[u] = new Floor(this, x, y);

                    if(!found) {
                        found = true;
                        sx = x;
                        sy = y;
                    }
                } else {
                    elements[u] = new Wall(this, x, y);
                }

                u++;
            }
        }
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
}

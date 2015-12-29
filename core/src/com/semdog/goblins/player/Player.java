package com.semdog.goblins.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.semdog.goblins.levels.Level;

/**
 * Created by Sam on 27-Dec-15.
 *
 * The player class!
 * Handles all the nice player movement, and things
 */
public class Player {
    private float x, y, dx, dy, dd, d;

    private PointLight playerLight;

    private ModelInstance nodeMarker;

    public Player(Vector2 spawn, Environment lightingEnvironment) {
        this.x = spawn.x * 10;
        this.y = spawn.y * 10;

        playerLight = new PointLight();
        playerLight.setColor(Color.YELLOW);
        playerLight.setIntensity(1);
        playerLight.setPosition(20, 0, 20);

        lightingEnvironment.add(playerLight);

        Model model = new ModelBuilder().createBox(1, 1, 1, new Material(ColorAttribute.createDiffuse(Color.RED)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Position);
        nodeMarker = new ModelInstance(model);
    }

    public void update(Level level, float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dd = 150;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dd = -150;
        } else {
            dd = 0;
        }

        dx = dy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W) && level.isFree(x + 5 * MathUtils.cosDeg(-d), 5, y + 5 * MathUtils.sinDeg(-d))) {
            dx += 30 * MathUtils.cosDeg(-d);
            dy += 30 * MathUtils.sinDeg(-d);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && level.isFree(x - 5 * MathUtils.cosDeg(-d), 5, y - 5 * MathUtils.sinDeg(-d))) {
            dx += -30 * MathUtils.cosDeg(-d);
            dy += -30 * MathUtils.sinDeg(-d);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && level.isFree(x - 5 * MathUtils.cosDeg(-d + 90), 5, y - 5 * MathUtils.sinDeg(-d + 90))) {
            dx += 30 * MathUtils.sinDeg(-d);
            dy += -30 * MathUtils.cosDeg(-d);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && level.isFree(x - 5 * MathUtils.cosDeg(-d - 90), 5, y - 5 * MathUtils.sinDeg(-d - 90))) {
            dx += -30 * MathUtils.sinDeg(-d);
            dy += 30 * MathUtils.cosDeg(-d);
        }

        x += dx * dt;
        y += dy * dt;

        playerLight.setPosition(x, 5, y);
        playerLight.setIntensity(50f);

        d += dd * dt;

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            level.playerActivated(x + 15 * MathUtils.cosDeg(-d), 5, y + 15 * MathUtils.sinDeg(-d));
        }
    }

    public void debugRender(ModelBatch modelBatch) {
        modelBatch.render(nodeMarker);
    }

    // TODO damn these units
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDd() {
        return dd;
    }
}

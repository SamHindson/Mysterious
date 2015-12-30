package com.semdog.goblins.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.levels.Level;

/**
 * Created by Sam on 27-Dec-15.
 *
 * The player class!
 * Handles all the nice player movement, and things
 */
public class Player {
    private float x, y, z, dx, dz, dd, d, walkTime;

    private PointLight playerLight;

    private ModelInstance nodeMarker;

    private Tool[] tools;

    private TextureRegion toolSlot;
    private int lastPickupSlot = -1, activeSlot = 0;

    public Player(Vector2 spawn, Environment lightingEnvironment) {
        this.x = spawn.x * 10;
        this.z = spawn.y * 10;

        playerLight = new PointLight();
        playerLight.setColor(Color.WHITE);
        playerLight.setIntensity(1);
        playerLight.setPosition(20, 0, 20);

        lightingEnvironment.add(playerLight);

        Model model = new ModelBuilder().createBox(1, 1, 1, new Material(ColorAttribute.createDiffuse(Color.RED)), VertexAttributes.Usage.Position);
        nodeMarker = new ModelInstance(model);

        tools = new Tool[5];

        toolSlot = TextureMaster.get("toolslot");
    }

    public void update(Level level, float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dd = 200;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dd = -200;
        } else {
            dd = 0;
        }

        level.playerIsAt(x, y, z);

        dx = dz = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W) && level.isFree(this, x + 5 * MathUtils.cosDeg(-d), 5, z + 5 * MathUtils.sinDeg(-d))) {
            dx += 30 * MathUtils.cosDeg(-d);
            dz += 30 * MathUtils.sinDeg(-d);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && level.isFree(this, x - 5 * MathUtils.cosDeg(-d), 5, z - 5 * MathUtils.sinDeg(-d))) {
            dx += -30 * MathUtils.cosDeg(-d);
            dz += -30 * MathUtils.sinDeg(-d);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && level.isFree(this, x - 5 * MathUtils.cosDeg(-d + 90), 5, z - 5 * MathUtils.sinDeg(-d + 90))) {
            dx += 30 * MathUtils.sinDeg(-d);
            dz += -30 * MathUtils.cosDeg(-d);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && level.isFree(this, x - 5 * MathUtils.cosDeg(-d - 90), 5, z - 5 * MathUtils.sinDeg(-d - 90))) {
            dx += -30 * MathUtils.sinDeg(-d);
            dz += 30 * MathUtils.cosDeg(-d);
        }

        x += dx * dt;
        z += dz * dt;

        if(dx != 0 || dz != 0) {
            walkTime += dt * 20;
            y = 7.5f + MathUtils.sin(walkTime) * 0.5f;
        } else {
            walkTime = 0;

            if(y != 7.5f) {
                y += (7.5f - y) / 10.f;
            }
        }

        playerLight.setPosition(x, 7.5f, z);
        playerLight.setIntensity(50f);

        d += dd * dt;

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            level.playerActivated(this, x + 15 * MathUtils.cosDeg(-d), 5, z + 15 * MathUtils.sinDeg(-d));
        }
    }

    public void drawHUD(SpriteBatch hudBatch) {
        if(tools[activeSlot] != null) {
            tools[activeSlot].render(hudBatch);
        }

        int size = 25;
        int sx = Gdx.graphics.getWidth() / 2 - (size * tools.length) / 2;

        for(int j = 0; j < tools.length; j++) {
            hudBatch.draw(toolSlot, sx + j * (size + 1), size, 0, 0, size, size, 1, 1, 0);
        }
    }

    public void debugRender(ModelBatch modelBatch) {
        modelBatch.render(nodeMarker);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getDd() {
        return dd;
    }

    public void addTool(Tool.ToolType pickupTool, Tool.Variant pickupVariant) {
        lastPickupSlot++;

        System.out.println("OW");

        if(lastPickupSlot == 5) {
            System.out.println("Cant!");
        } else {
            tools[lastPickupSlot] = new Sword(this, pickupVariant);
        }
    }
}

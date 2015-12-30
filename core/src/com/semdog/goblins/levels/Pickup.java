package com.semdog.goblins.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.player.Player;
import com.semdog.goblins.player.Tool;

/**
 * Created by Sam on 30-Dec-15.
 */
public class Pickup extends LevelElement {

    private Tool.ToolType pickupTool;
    private Tool.Variant pickupVariant;

    private Decal decal;

    private boolean got = false;
    private float bobOffset = 0;

    public Pickup(Level level, int x, int y, int z, String toolType, String variant) {
        super(level, x, y, z);

        System.out.println("Screw it, you're getting a generic sword");

        pickupTool = Tool.ToolType.SWORD;
        pickupVariant = Tool.Variant.STANDARD;

        decal = Decal.newDecal(5, 5, TextureMaster.get("sworddecal"), true);
    }

    @Override
    public void update(Level level, float dt) {
        decal.rotateY(dt * 100);
        bobOffset = MathUtils.sin(level.getAge() * 2) / 2.f;

        decal.setPosition(x * 10, y * 10 + bobOffset - 5, z * 10);
    }

    @Override
    public void render(DecalBatch decalBatch, ModelBatch modelBatch, Environment environment) {
        if (!got) {
            decalBatch.add(decal);
        }
    }

    @Override
    public boolean isTraverisble() {
        return true;
    }

    @Override
    public void activate(Player player) {
        if(!got) {
            got = true;

            player.addTool(pickupTool, pickupVariant);
        }
    }

    @Override
    public void touchedByPlayer(Player player) {
        activate(player);
    }
}

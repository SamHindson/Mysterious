package com.semdog.goblins.levels;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.player.Player;

/**
 * Created by Sam on 28-Dec-15.
 *
 * This is a door that drops when the player activates it.
 */
public class DropDoor extends LevelElement implements Activatible {

    private boolean dropping = false;
    private boolean dropped = false;

    private int aid;
    private float eh = 5;

    private static Model baseModel;

    static {
        baseModel = new ModelBuilder().createBox(10, 10, 10,
                new Material(TextureAttribute.createDiffuse(TextureMaster.get("wall3"))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }

    public DropDoor(Level level, int x, int y, int z, int aid) {
        super(level, x, y, z);
        model = new ModelInstance(baseModel);
        model.transform.setToTranslation(x * 10, y * 10 - 5, z * 10);

        this.aid = aid;
    }

    @Override
    public void update(Level level, float dt) {
        if(dropping) {
            eh -= dt * 10;
            model.transform.setToTranslation(x * 10, eh, z * 10);

            if(eh <= -5) {
                eh = -4.9f;
                y = 0;
                dropping = false;
                dropped = true;
            }
        }
    }

    @Override
    public void touchedByPlayer(Player player) {

    }

    @Override
    public void render(DecalBatch decalBatch, ModelBatch modelBatch, Environment environment) {
        modelBatch.render(model, environment);
    }

    /**
     * If the door has dropped, the player should be able to walk through it. Yeah?
     * @return come on dude
     */
    @Override
    public boolean isTraverisble() {
        return dropped;
    }

    @Override
    public void activate(Player player) {
        if(!dropped && !dropping) {
            dropping = true;
        }
    }

    @Override
    public int getID() {
        return aid;
    }
}

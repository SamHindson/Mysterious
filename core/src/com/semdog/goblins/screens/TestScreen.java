package com.semdog.goblins.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.levels.Level;

/**
 * Created by Sam on 26-Dec-15.
 */
public class TestScreen implements GoblinScreen {

    private ScreenManager manager;

    private PerspectiveCamera camera;

    private ModelBatch modelBatch;
    private Environment lightingEnvironment;

    private Level level;

    private PointLight playerLight;

    private float px, py, pd, dd, dx, dy;

    //private CameraInputController controller;

    public TestScreen(ScreenManager manager) {
        this.manager = manager;

        TextureMaster.init();
    }

    @Override
    public void show() {
        lightingEnvironment = new Environment();
        lightingEnvironment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 1));

        level = new Level(lightingEnvironment);

        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.direction.set(1, 0, 0);
        camera.near = 1;
        camera.far = 508;
        camera.update();

        CameraGroupStrategy strategy = new CameraGroupStrategy(camera);

        modelBatch = new ModelBatch();

        //controller = new CameraInputController(camera);
        //Gdx.input.setInputProcessor(controller);

        px = level.getSpawn().x * 10;
        py = level.getSpawn().y * 10;

        playerLight = new PointLight();
        playerLight.setColor(Color.GREEN);
        playerLight.setIntensity(1);
        playerLight.setPosition(20, 0, 20);

        lightingEnvironment.add(playerLight);
    }

    @Override
    public void update(float dt) {
        level.update(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dd = 150;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dd = -150;
        } else {
            dd = 0;
        }

        float xe = 0;
        float ye = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            xe += 30 * MathUtils.cosDeg(-pd);
            ye += 30 * MathUtils.sinDeg(-pd);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            xe += -30 * MathUtils.cosDeg(-pd);
            ye += -30 * MathUtils.sinDeg(-pd);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xe += 30 * MathUtils.sinDeg(-pd);
            ye += -30 * MathUtils.cosDeg(-pd);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xe += -30 * MathUtils.sinDeg(-pd);
            ye += 30 * MathUtils.cosDeg(-pd);
        }

        px += xe * dt;
        py += ye * dt;

        playerLight.setPosition(px, 5, py);
        playerLight.setIntensity(25f);

        //px = 0;
        //px += dt * 5;

        //controller.update();
        camera.position.set(px, 7, py);

        //camera.direction.set(Vector3.X);

        pd += dd * dt;

        camera.rotate(Vector3.Y, dd * dt);
        camera.update();

        //camera.direction.set(Vector3.X);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 0.9f);

        modelBatch.begin(camera);
        level.render(modelBatch, lightingEnvironment);
        modelBatch.end();
    }

    @Override
    public boolean isReady() {
        return !TextureMaster.loading;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

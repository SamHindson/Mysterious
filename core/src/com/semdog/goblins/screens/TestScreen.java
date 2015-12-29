package com.semdog.goblins.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;
import com.semdog.goblins.graphics.TextureMaster;
import com.semdog.goblins.levels.Level;
import com.semdog.goblins.player.Player;

/**
 * Created by Sam on 26-Dec-15.
 *
 * Just a test screen. It'll probably get changed into the play screen but w/e
 */
public class TestScreen implements GoblinScreen {

    private ScreenManager manager;

    private PerspectiveCamera camera;

    private ModelBatch modelBatch;
    private DecalBatch decalBatch;
    private Environment lightingEnvironment;

    private Level level;
    private Player player;

    private ColorAttribute ambientLight;

    public TestScreen(ScreenManager manager) {
        this.manager = manager;

        TextureMaster.init();
    }

    @Override
    public void show() {
        ambientLight = new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.4f, 1);
        lightingEnvironment = new Environment();
        lightingEnvironment.set(ambientLight);

        level = new Level(lightingEnvironment);

        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.direction.set(1, 0, 0);
        camera.near = 1;
        camera.far = 508;
        camera.update();

        CameraGroupStrategy strategy = new CameraGroupStrategy(camera);

        modelBatch = new ModelBatch();
        decalBatch = new DecalBatch(strategy);

        //controller = new CameraInputController(camera);
        //Gdx.input.setInputProcessor(controller);

        player = new Player(level.getSpawn(), lightingEnvironment);
    }

    @Override
    public void update(float dt) {
        level.update(dt);
        player.update(level, dt);

        camera.far = 500;
        ambientLight.color.set(0.2f, 0.2f, 0.2f, 1.0f);

        camera.position.set(player.getX(), 7.5f, player.getY());

        camera.rotate(Vector3.Y, player.getDd() * dt);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 0.9f);

        modelBatch.begin(camera);
        level.render(modelBatch, lightingEnvironment);
        //player.debugRender(modelBatch);
        modelBatch.end();
    }

    @Override
    public boolean isReady() {
        return !TextureMaster.loading;
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
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

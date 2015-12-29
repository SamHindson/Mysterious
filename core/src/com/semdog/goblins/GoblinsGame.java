package com.semdog.goblins;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.semdog.goblins.screens.ScreenManager;

/**
 * Created by Sam on 30-Feb-10
 *
 * The entry point for...
 *          eheheh....
 *       MYSTERIOUS GHOULS & GOBLINS!
 */

public class GoblinsGame extends ApplicationAdapter {

    private ScreenManager screenManager;

    @Override
    public void create() {
        screenManager = new ScreenManager();
    }

    @Override
    public void render() {
        screenManager.update(Gdx.graphics.getDeltaTime());
        screenManager.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

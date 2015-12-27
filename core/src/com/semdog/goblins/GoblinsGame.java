package com.semdog.goblins;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.semdog.goblins.screens.ScreenManager;

public class GoblinsGame extends ApplicationAdapter {

    private ScreenManager screenManager;

    @Override
    public void create() {
        Gdx.app.log("Game", "Yea!");
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

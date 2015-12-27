package com.semdog.goblins.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by Sam on 26-Dec-15.
 */
public class ScreenManager {
    private GoblinScreen screen;

    private boolean loading = true;

    public ScreenManager() {
        screen = new TestScreen(this);
    }

    public void update(float dt) {
        if(loading) {
            if(screen.isReady()) {
                loading = false;
                screen.show();
            }

            Gdx.app.log("ScreenManager", "Loading...");
        } else {
            screen.update(dt);
        }
    }

    public void render() {
        if(loading) {

        } else {
            screen.render(0);
        }
    }
}

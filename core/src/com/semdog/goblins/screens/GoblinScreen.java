package com.semdog.goblins.screens;

import com.badlogic.gdx.Screen;

/**
 * Created by Sam on 26-Dec-15.
 */
public interface GoblinScreen extends Screen {
    public void update(float dt);

    public boolean isReady();
}

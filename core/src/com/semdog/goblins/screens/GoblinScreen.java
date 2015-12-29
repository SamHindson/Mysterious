package com.semdog.goblins.screens;

import com.badlogic.gdx.Screen;

/**
 * Created by Sam on 26-Dec-15.
 *
 * A Screen interface that basically just allows a GDX screen to tell if it's finished loading.
 * It also distinguishes 'update' from 'render'
 */
public interface GoblinScreen extends Screen {
    void update(float dt);
    boolean isReady();
}

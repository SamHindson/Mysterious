package com.semdog.goblins.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sam on 26-Dec-15.
 *
 * The Screen Manager which handles the screens and shows loading screens when needed.
 */
public class ScreenManager {
    private GoblinScreen screen;

    private boolean loading = true;

    private BitmapFont loadingFont;
    private SpriteBatch loadingBatch;
    private float centerOffset;
    private String loadingText = "Loading...";

    public ScreenManager() {
        loadingFont = new BitmapFont(Gdx.files.internal("dos16.fnt"));
        loadingBatch = new SpriteBatch();

        GlyphLayout layout = new GlyphLayout();
        layout.setText(loadingFont, loadingText);
        centerOffset = layout.width / 2;

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
            loadingBatch.begin();
            loadingFont.draw(loadingBatch, "Loading...", Gdx.graphics.getWidth() / 2 - centerOffset, Gdx.graphics.getHeight() / 2);
            loadingBatch.end();
        } else {
            screen.render(0);
        }
    }
}

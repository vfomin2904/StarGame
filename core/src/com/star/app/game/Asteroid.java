package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Vector2 position;
    private float angle;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public float getAngle() {
        return angle;
    }

    public Asteroid() {
        this.position = new Vector2(ScreenManager.SCREEN_WIDTH +256, ScreenManager.SCREEN_HEIGHT / 2);
        this.angle = MathUtils.random(220.0f, 160.0f);
    }

    public void deactivate() {
        active = false;
    }

    public void update(float dt) {
        position.x += MathUtils.cosDeg(angle) * 240.0f * dt;
        position.y += MathUtils.sinDeg(angle) * 240.0f * dt;

        if (position.x < -400) {
            position.x = ScreenManager.SCREEN_WIDTH + 256;
            position.y = MathUtils.random(128, ScreenManager.SCREEN_HEIGHT - 128);
            angle = MathUtils.random(220.0f, 160.0f);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void activate(float x, float y) {
        position.set(x, y);
        active = true;
    }
}
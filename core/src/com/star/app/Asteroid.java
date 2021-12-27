package com.star.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Texture texture;
    private Vector2 position;
    private float angle;

    public Asteroid() {
        this.texture = new Texture("asteroid.png");
        this.position = new Vector2(ScreenManager.SCREEN_WIDTH +256, ScreenManager.SCREEN_HEIGHT / 2);
        this.angle = MathUtils.random(220.0f, 160.0f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 128, position.y - 128, 128, 128,
                256, 256, 1, 1,
                angle, 0, 0, 256, 256, false, false);
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
}

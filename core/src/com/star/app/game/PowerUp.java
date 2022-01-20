package com.star.app.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;

public class PowerUp implements Poolable {
    public enum Type {
        MEDKIT(0), MONEY(1), AMMOS(2);

        int index;

        Type(int index) {
            this.index = index;
        }
    }

    private GameController gc;
    private Vector2 position;
    private Vector2 velocity;
    private float time;
    private boolean active;
    private Type type;
    private int power;
    private Circle hitArea;

    public Type getType() {
        return type;
    }

    public float getTime() {
        return time;
    }

    public int getPower() {
        return power;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public Circle getHitArea() {
        return hitArea;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void deactivate() {
        active = false;
    }

    public PowerUp(GameController gc) {
        this.gc = gc;
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.active = false;
        this.hitArea = new Circle(position, 100);
    }

    public void activate(Type type, float x, float y, int power) {
        this.type = type;
        this.position.set(x, y);
        this.velocity.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
        this.velocity.nor().scl(50.0f);
        this.active = true;
        this.power = power;
        this.time = 0.0f;
    }

    public void update(float dt) {

        if (hitArea.overlaps(gc.getHero().getHitArea())) {
            Vector2 heroPosition = gc.getHero().getPosition();
            velocity = new Vector2(heroPosition.x, heroPosition.y).sub(new Vector2(position.x, position.y));
        }

        position.mulAdd(velocity, dt);
        hitArea.setPosition(position);
        time += dt;
        if (time >= 7.0f) {
            deactivate();
        }
    }
}

package com.star.app.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

public class Bot extends Ship{

    public Bot(GameController gc) {
        super(gc, 100, 500);
        this.position = new Vector2(ScreenManager.SCREEN_WIDTH - 50, ScreenManager.SCREEN_HEIGHT / 2);
        this.velocity = new Vector2(0, 0);
        this.texture = Assets.getInstance().getAtlas().findRegion("ship");
        this.hitArea = new Circle(position, 29);
    }

    public void update(float dt) {
        super.update(dt);
        Vector2 heroPosition = gc.getHero().getPosition();
        Vector2 deltaVector = new Vector2(heroPosition.x - position.x, heroPosition.y - position.y);

        this.angle = deltaVector.angleDeg() + MathUtils.random(-2.f, 2.f);
        if (MathUtils.random(100.f) < 10.f) {
            tryToFire();
        }

        if (deltaVector.len() > 5 * hitArea.radius) {
            this.velocity.x += MathUtils.cosDeg(angle) * enginePower * dt;
            this.velocity.y += MathUtils.sinDeg(angle) * enginePower * dt;
        } else {
            this.velocity.x -= MathUtils.cosDeg(angle) * enginePower * dt;
            this.velocity.y -= MathUtils.sinDeg(angle) * enginePower * dt;
        }

    }
}

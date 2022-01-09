package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

public class WorldRenderer {
    private GameController gc;
    private SpriteBatch batch;
    private BitmapFont font32;
    private BitmapFont hpText;
    private StringBuilder sb;

    public WorldRenderer(GameController gc, SpriteBatch batch) {
        this.gc = gc;
        this.batch = batch;
        this.font32 = Assets.getInstance().getAssetManager().get("fonts/font32.ttf", BitmapFont.class);
        this.hpText = Assets.getInstance().getAssetManager().get("fonts/font32.ttf", BitmapFont.class);
        this.sb = new StringBuilder();
    }

    public void render() {
        ScreenUtils.clear(0.0f, 0.1f, 0.5f, 1);
        batch.begin();
        gc.getBackground().render(batch);
        gc.getAsteroidController().render(batch);
        gc.getBulletController().render(batch);
        gc.getHero().render(batch);
        sb.setLength(0);
        sb.append("SCORE: ").append(gc.getHero().getScoreView());
        font32.draw(batch, sb, 20, 700);
        hpText.draw(batch, gc.getHero().getHealth() + " HP", ScreenManager.SCREEN_WIDTH - 100, 700);

        if (!gc.getHero().isActive()) {
            font32.draw(batch, "Game over", ScreenManager.SCREEN_WIDTH / 2 - 60, ScreenManager.SCREEN_HEIGHT / 2);
        }

        batch.end();
    }
}

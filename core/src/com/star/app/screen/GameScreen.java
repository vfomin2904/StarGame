package com.star.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.GameController;
import com.star.app.game.WorldRenderer;
import com.star.app.screen.utils.Assets;

public class GameScreen extends AbstractScreen {
    private GameController gc;
    private WorldRenderer worldRenderer;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.GAME);
        if (this.gc == null || this.worldRenderer == null) {
            this.gc = new GameController();
            this.worldRenderer = new WorldRenderer(gc, batch);
        }
    }

    @Override
    public void render(float delta) {
        gc.update(delta);
        worldRenderer.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
        }
    }
}

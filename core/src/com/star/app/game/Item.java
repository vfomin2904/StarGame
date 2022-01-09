package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

public class Item implements Poolable {
    private GameController gc;
    private TextureRegion texture;
    private Vector2 position;
    private boolean active;
    private Circle hitArea;
    private ItemType itemType;

    private final float BASE_SIZE = 64;
    private final float BASE_RADIUS = BASE_SIZE / 2;

    public Circle getHitArea() {
        return hitArea;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Item(GameController gc, Vector2 position) {
        this.itemType = ItemType.getRandomItemType();
        this.gc = gc;
        this.texture = Assets.getInstance().getAtlas().findRegion(itemType.getTexture());
        this.position = position;
        this.hitArea = new Circle(0, 0, 0);
        this.active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - itemType.getWidth()/2, position.y - itemType.getHeight()/2, itemType.getWidth()/2, itemType.getHeight()/2,
                itemType.getHeight(), itemType.getWidth(), 1.f, 1.f, 0);
    }

    public void deactivate() {
        active = false;
    }

    public void update(float dt) {
        hitArea.setPosition(position);
    }

    public void activate(float x, float y) {
        position.set(x, y);
        active = true;
        hitArea.setPosition(position);
        hitArea.setRadius(itemType.getHeight());
    }
}

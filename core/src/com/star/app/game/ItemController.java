package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.ObjectPool;

public class ItemController extends ObjectPool<Item> {
    private GameController gc;

    @Override
    protected Item newObject() {
        return new Item(gc, new Vector2(0, 0));
    }

    public ItemController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Item a = activeList.get(i);
            a.render(batch);
        }
    }

    public void setup(float x, float y){
        getActiveElement().activate(x, y);
    }

    public void update(float dt){
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}

package com.star.app.game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum ItemType {
    HEALTH("health", 61, 36), BULLET("ammunition", 37, 36), COIN("coin", 37, 37);

    private String texture;
    private int height;
    private int width;
    private final static List<ItemType> types = Arrays.asList(values());
    private final static int SIZE = types.size();

    ItemType(String texture, int height, int width) {
        this.texture = texture;
        this.height = height;
        this.width = width;
    }

    public String getTexture() {
        return texture;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static ItemType getRandomItemType() {
        Random random = new Random();
        return types.get(random.nextInt(SIZE));
    }
}

package com.aor.components;

import com.aor.utils.Vector2d;

public class BoxCollider extends Node {
    private int width;
    private int height;

    public BoxCollider(Vector2d position, int width, int height) {
        super(position);
        this.width = width;
        this.height = height;
    }


    public boolean isColliding(BoxCollider other) {
        float x = getPosition().getX();
        float y = getPosition().getY();

        float otherX = other.getPosition().getX();
        float otherY = other.getPosition().getY();
        float otherWidth = other.getWidth();
        float otherHeight = other.getHeight();

        return x < (otherX + otherWidth) && (x + width) > otherX && y < (otherY + otherHeight) && (y + height) > otherY;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
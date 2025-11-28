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

    private boolean checkTopLeftCollision(BoxCollider other) {
        float x = getPosition().getX();
        float y = getPosition().getY();

        float otherX = other.getPosition().getX();
        float otherY = other.getPosition().getY();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        //Check if topLeft is inside
        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    private boolean checkBotRightCollision(BoxCollider other) {
        float x = getPosition().getX() + width;
        float y = getPosition().getY() + height;

        float otherX = other.getPosition().getX();
        float otherY = other.getPosition().getY();
        float otherWidth = other.getWidth();
        float otherHeight = other.getHeight();

        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    public boolean isColliding(BoxCollider other) {
        if (other.equals(this)) return false;
        return checkBotRightCollision(other) || checkTopLeftCollision(other);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
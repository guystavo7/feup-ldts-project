package com.aor.utils;

public class BoxCollider extends Node {
    private int width;
    private int height;

    public BoxCollider(Vector2d position, int width, int height) {
        super(position);
        this.width = width;
        this.height = height;
    }

    private boolean checkTopLeftCollision(Vector2d newPos, BoxCollider other) {
        int x = newPos.getX();
        int y = newPos.getY();

        int otherX = other.getPosition().getX();
        int otherY = other.getPosition().getY();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        //Check if topLeft is inside
        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    private boolean checkBotRightCollision(Vector2d newPos, BoxCollider other) {
        int x = newPos.getX() + width;
        int y = newPos.getY() + height;

        int otherX = other.getPosition().getX();
        int otherY = other.getPosition().getY();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    public boolean isColliding(Vector2d newPos, BoxCollider other) {
        if (other.equals(this)) return false;
        return checkBotRightCollision(newPos, other) || checkTopLeftCollision(newPos, other);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


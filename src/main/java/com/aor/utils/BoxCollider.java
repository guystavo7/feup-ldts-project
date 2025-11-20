package com.aor.utils;

public class BoxCollider extends Node {
    private int width;
    private int height;

    public BoxCollider(Vector2d vector2d, int width, int height) {
        super(vector2d);
        this.width = width;
        this.height = height;
    }

    private boolean checkTopleftCollision(BoxCollider other) {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        int otherX = other.getPosition().getX();
        int otherY = other.getPosition().getY();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        //Check if topLeft is inside
        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    private boolean checkBotLeftCollision(BoxCollider other) {
        int x = this.getPosition().getX() + width;
        int y = this.getPosition().getY() + height;

        int otherX = other.getPosition().getX();
        int otherY = other.getPosition().getY();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }



    public boolean isColliding(BoxCollider other) {
        return checkBotLeftCollision(other) || checkTopleftCollision(other);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


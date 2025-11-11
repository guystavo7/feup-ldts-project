package com.framework;

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

        //Check if topLeft is inside
        return (otherX > x && otherX < (x + width) && otherY > y && otherY < (y + height));
    }

    private boolean checkBotLeftCollision(BoxCollider other) {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        int otherX = other.getPosition().getX();
        int otherY = other.getPosition().getY();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        return ((otherX + otherWidth) > x && otherX < (x + width) && (otherY + otherHeight) > y && otherY < (y + height));
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


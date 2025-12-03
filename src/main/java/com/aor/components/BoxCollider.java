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
        double x = getPosition().getX();
        double y = getPosition().getY();

        double otherX = other.getPosition().getX();
        double otherY = other.getPosition().getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        //Check if topLeft is inside
        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    private boolean checkTopRightCollision(BoxCollider other) {
        double x = getPosition().getX() + width;
        double y = getPosition().getY();

        double otherX = other.getPosition().getX();
        double otherY = other.getPosition().getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        //Check if topLeft is inside
        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    private boolean checkBotRightCollision(BoxCollider other) {
        double x = getPosition().getX() + width;
        double y = getPosition().getY() + height;

        double otherX = other.getPosition().getX();
        double otherY = other.getPosition().getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    private boolean checkBotLeftCollision(BoxCollider other) {
        double x = getPosition().getX() ;
        double y = getPosition().getY() + height;

        double otherX = other.getPosition().getX();
        double otherY = other.getPosition().getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        return x > otherX && x < (otherX + otherWidth) && y > otherY && y < (otherY + otherHeight);
    }

    public boolean isColliding(BoxCollider other) {
        if (other.equals(this)) return false;
        return checkBotRightCollision(other) || checkTopLeftCollision(other)||checkTopRightCollision(other)||checkBotLeftCollision(other);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
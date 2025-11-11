package com.framework;

public class BoxCollider extends Node {
    private int width;
    private int height;

    public BoxCollider(Vector2d vector2d, int width, int height) {
        super(vector2d);
        this.width = width;
        this.height = height;
    }

    private boolean isColliding(BoxCollider other) {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        //Check if topLeft is inside
        if (other.getPosition().getX() > x && other.getPosition().getY() < (x + width)) {
            if (other.getPosition().getY() > y && other.getPosition().getY() < (y + height)) {
                return true;
            }
        }

        //Check if bottomRight is inside
        if (other.getPosition().getX() + other.getWidth() > x && other.getPosition().getX() < (x + width)) {
            if (other.getPosition().getY() + other.getHeight() > y && other.getPosition().getY() < (y + height)) {
                return true;
            }
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


package com.framework;

public class BoxCollider extends Node {
    private int width, height;

    public BoxCollider(Vector2d vector2d, int width, int height) {
        super(vector2d);
        this.width = width;
        this.height = height;
    }

    //Todo - takes a other boxCollider and check if there is intersection
    public boolean isColliding(BoxCollider other) {
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

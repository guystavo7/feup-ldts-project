package com.aor.utils;

public class BoxCollider extends Node {
    private int width, height;

    public BoxCollider(Vector2d vector2d, int width, int height) {
        super(vector2d);
        this.width = width;
        this.height = height;
    }

    //Todo - takes a other boxCollider and check if there is intersection
    private boolean isColliding(BoxCollider other) {
        return false;
    }
}

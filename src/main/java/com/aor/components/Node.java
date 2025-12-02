package com.aor.components;

import com.aor.utils.Vector2d;

public abstract class Node {
    private Vector2d position;

    protected Node(Vector2d vector2d) {
        this.position = vector2d;
    }

    public void update(Vector2d newPos) {
        this.position = newPos;
    }

    //Getters and Setters
    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}

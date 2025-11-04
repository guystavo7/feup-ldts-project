package com.framework;

public abstract class Node {
    private Vector2d vector2d;

    public Node(Vector2d vector2d) {
        this.vector2d = vector2d;
    }

    public void update(Vector2d newPos) {
        this.vector2d = newPos;
    }

    //Todo - setter and getters
}

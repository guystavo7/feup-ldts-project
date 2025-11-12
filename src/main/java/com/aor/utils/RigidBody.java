package com.aor.utils;

public class RigidBody extends Node {
    Vector2d velocity;

    public RigidBody(Vector2d vector2d, Vector2d velocity) {
        super(vector2d);
        this.velocity = velocity;
    }


    //Todo - should calculate the new position (old position + velocity) and check if there is collisions
    public Vector2d move() {
        Vector2d newPos = new Vector2d(0, 0);
        super.update(newPos);

        return newPos;
    }

    //Todo setter and getters
}
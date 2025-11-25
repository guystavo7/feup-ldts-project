package com.aor.utils;

import com.aor.game.Arena;

import javax.swing.*;

public class GenericController {
    private GenericModel model;
    private RigidBody rigidBody;
    private BoxCollider boxCollider;

    public GenericController(GenericModel model, Vector2d initialVelocity, int width, int height, Arena arena) {
        this.model = model;
        boxCollider = new BoxCollider(model.getPosition(), width, height);
        rigidBody = new RigidBody(model.getPosition(), initialVelocity, arena, boxCollider);
    }

    public RigidBody getRigidBody() {
        return rigidBody;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public GenericModel getModel() {
        return model;
    }
}


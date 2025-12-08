package com.aor.components;

import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public abstract class Node {
    private Vector2d position;
    private Vector2d offset;

    protected Node(Vector2d position, Vector2d offset) {
        this.position = Utils.vectorSum(position, offset);
        this.offset = offset;
    }

    public void update(Vector2d newPos) {
        this.position = Utils.vectorSum(newPos, offset);
    }

    //Getters and Setters
    public Vector2d getPosition() {
        return Utils.vectorSum(position, offset);
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}

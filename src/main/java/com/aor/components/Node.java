package com.aor.components;

import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public abstract class Node {
    private Vector2d position;
    private Vector2d offset;

    protected Node(Vector2d position) {
        this.position = position;
        this.offset = new Vector2d(0, 0);
    }

    protected Node(Vector2d position, Vector2d offset) {
        this.position = Utils.vectorSum(offset, position);
        this.offset = offset;
    }

    public void update(Vector2d newPos) {
        this.position = Utils.vectorSum(newPos, offset);
    }

    //Getters and Setters
    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public void setOffset(Vector2d offset) {
        this.offset = offset;
    }
}

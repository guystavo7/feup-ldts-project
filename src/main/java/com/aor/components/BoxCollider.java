package com.aor.components;

import com.aor.utils.Vector2d;

import javax.swing.*;

public class BoxCollider extends Node {
    private int width;
    private int height;
    private String tag;

    public BoxCollider(Vector2d position, int width, int height, String tag) {
        super(position);
        this.width = width;
        this.height = height;
        this.tag = tag;
    }

    public BoxCollider(Vector2d position, Vector2d offset, int width, int height, String tag) {
        super(position, offset);
        this.width = width;
        this.height = height;
        this.tag = tag;
    }


    public boolean isColliding(BoxCollider other) {
        if (other.equals(this)) return false;

        double x = getPosition().getX();
        double y = getPosition().getY();

        double otherX = other.getPosition().getX();
        double otherY = other.getPosition().getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        return x < (otherX + otherWidth) && (x + width) > otherX && y < (otherY + otherHeight) && (y + height) > otherY;

    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getTag() {
        return tag;
    }
}


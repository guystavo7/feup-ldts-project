package com.aor.utils;

import java.util.Objects;

public class Vector2d {
    private float x;
    private float y;

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //Some useful methods
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;

        Vector2d o = (Vector2d) other;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vector2d addVector(Vector2d other) {
        this.x += other.getX();
        this.y += other.getY();

        return this;
    }

    public Vector2d scalarProduct(int k) {
        this.x *= k;
        this.y *= k;

        return this;
    }

    //To do: setters and getters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}

package com.framework;

import java.util.Objects;

public class Vector2d {
    private int x;
    private int y;

    public Vector2d(int x, int y) {
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
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

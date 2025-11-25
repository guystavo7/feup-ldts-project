package com.aor.utils;

public class GenericModel {
    private Vector2d position;
    private Vector2d velocity;
    private Vector2d direction;

    public GenericModel(Vector2d position, Vector2d velocity, Vector2d direction) {
        this.position = position;
        this.velocity = velocity;
        this.direction = direction;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public void setDirectionX(int newDirX) {
        this.direction.setX(newDirX);
    }

    public void setDirectionY(int newDirY) {
        this.direction.setY(newDirY);
    }

    public Vector2d getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public Vector2d getDirection() {
        return direction;
    }

}

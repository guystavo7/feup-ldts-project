package com.aor.utils;

import com.aor.game.Arena;

import javax.swing.text.Position;
import java.util.List;
import java.util.Vector;

public class RigidBody extends Node {
    private final Arena arena;
    private BoxCollider boxCollider;

    private Vector2d velocity;

    public RigidBody(Vector2d position, Vector2d velocity, Arena arena, BoxCollider boxCollider) {
        super(position);
        this.velocity = velocity;
        this.arena = arena;
        this.boxCollider = boxCollider;
    }

    public Vector2d move(Vector2d currentPos) {
        Vector2d newPos = Utils.vectorSum(currentPos, velocity);
        List<BoxCollider> boxColliders = arena.detectCollision(this.boxCollider);

        for (BoxCollider collider : boxColliders) {
            //x-axis adjustment
            if (velocity.getX() > 0 && (newPos.getX() + this.boxCollider.getWidth()) > collider.getPosition().getX()) {
                newPos.setX(collider.getPosition().getX());
            } else if (velocity.getX() < 0 && newPos.getX() < (collider.getPosition().getX() + collider.getWidth())) {
                newPos.setX(collider.getPosition().getX() + collider.getWidth());
            }

            //After adjusting x-position, check if is still colliding
            if (!collider.isColliding(this.boxCollider)) continue;

            //y-axis adjustment
            if (velocity.getY() > 0 && (newPos.getY() + this.boxCollider.getHeight()) > collider.getPosition().getY()) {
                newPos.setY(collider.getPosition().getY());
            } else if (velocity.getY() < 0 && newPos.getY() < (collider.getPosition().getY() + collider.getHeight())) {
                newPos.setY(collider.getPosition().getY() + collider.getHeight());
            }

            boxCollider.update(newPos);
        }

        return newPos;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }


}
package com.aor.components;

import com.aor.game.Arena;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

import java.util.List;

public class RigidBody extends Node {
    private final Arena arena;
    private final BoxCollider boxCollider;

    private Vector2d velocity;

    public RigidBody(Vector2d position, Vector2d velocity, Arena arena, BoxCollider boxCollider) {
        super(position);
        this.velocity = velocity;
        this.arena = arena;
        this.boxCollider = boxCollider;
    }

    private float moveX(Vector2d pos) {
        Vector2d newPos = Utils.vectorSum(new Vector2d(velocity.getX(), 0), pos);
        List<BoxCollider> boxColliders = arena.detectCollision(newPos, this.boxCollider);

        for (BoxCollider collider : boxColliders) {
            if (velocity.getX() > 0 && (newPos.getX() + this.boxCollider.getWidth()) > collider.getPosition().getX()) {
                newPos.setX(collider.getPosition().getX() - this.boxCollider.getWidth());
            } else if (velocity.getX() < 0 && newPos.getX() < (collider.getPosition().getX() + collider.getWidth())) {
                newPos.setX(collider.getPosition().getX() + collider.getWidth());
            }
        }

        return newPos.getX();
    }

    private float moveY(Vector2d pos) {
        Vector2d newPos = Utils.vectorSum(new Vector2d(0, velocity.getY()), pos);
        List<BoxCollider> boxColliders = arena.detectCollision(newPos, this.boxCollider);

        for (BoxCollider collider : boxColliders) {
            if (velocity.getY() > 0 && (newPos.getY() + this.boxCollider.getHeight()) > collider.getPosition().getY()) {
                newPos.setY(collider.getPosition().getY() - this.boxCollider.getHeight());
            } else if (velocity.getY() < 0 && newPos.getY() < (collider.getPosition().getY() + collider.getHeight())) {
                newPos.setY(collider.getPosition().getY() + collider.getHeight());
            }
        }

        return newPos.getY();
    }

    public Vector2d move() {
        float x = moveX(getPosition());
        float y = moveY(new Vector2d(x, getPosition().getY()));
        return new Vector2d(x, y);
    }

    @Override
    public void update(Vector2d position) {
        super.update(position);
        boxCollider.update(position);
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
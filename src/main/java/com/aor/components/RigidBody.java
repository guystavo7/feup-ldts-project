package com.aor.components;

import java.util.List;

import com.aor.game.Arena;
import com.aor.game.GameData;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

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

    public RigidBody(Vector2d position, Vector2d offset, Vector2d velocity, Arena arena, BoxCollider boxCollider) {
        super(position, offset);
        this.velocity = velocity;
        this.arena = arena;
        this.boxCollider = boxCollider;
    }

    private double moveX(Vector2d pos, String tag) {
        Vector2d newPos = Utils.vectorSum(new Vector2d(velocity.getX(), 0), pos);
        List<BoxCollider> boxColliders = arena.detectCollision(newPos, this.boxCollider, tag);

        for (BoxCollider collider : boxColliders) {
            if (velocity.getX() > 0 && (newPos.getX() + this.boxCollider.getWidth()) > collider.getPosition().getX()) {
                newPos.setX(collider.getPosition().getX() - this.boxCollider.getWidth());
                velocity.setX(0);
            } else if (velocity.getX() < 0 && newPos.getX() < (collider.getPosition().getX() + collider.getWidth())) {
                newPos.setX(collider.getPosition().getX() + collider.getWidth());
                velocity.setX(0);
            }
        }

        return newPos.getX();
    }

    private double moveY(Vector2d pos, String tag) {
        Vector2d newPos = Utils.vectorSum(new Vector2d(0, velocity.getY()), pos);
        List<BoxCollider> boxColliders = arena.detectCollision(newPos, this.boxCollider, tag);

        for (BoxCollider collider : boxColliders) {
            if (velocity.getY() > 0 && (newPos.getY() + this.boxCollider.getHeight()) > collider.getPosition().getY()) {
                newPos.setY(collider.getPosition().getY() - this.boxCollider.getHeight());
            } else if (velocity.getY() < 0 && newPos.getY() < (collider.getPosition().getY() + collider.getHeight())) {
                newPos.setY(collider.getPosition().getY() + collider.getHeight());
            }

            velocity.setY(0);
        }

        return newPos.getY();
    }

    public Vector2d move() {
        double x = moveX(getPosition(), GameData.TILE_TAG);
        double y = moveY(new Vector2d(x, getPosition().getY()), GameData.TILE_TAG);
        return new Vector2d(x, y);
    }

    @Override
    public void update(Vector2d position) {
        super.update(position);
        boxCollider.update(position);
    }

    @Override
    public void update() {

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
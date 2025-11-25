package com.aor.utils;

import com.aor.game.Arena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class TestRigidBody {
    RigidBody rb;
    Arena stubArena;

    @BeforeEach
    void setUp() {
        //Rectangle with topLeft (5, 5) and size (3, 10)
        Vector2d position = new Vector2d(5, 5);
        Vector2d velocity = new Vector2d(0, 0);
        stubArena = Mockito.mock(Arena.class);
        BoxCollider boxCollider = new BoxCollider(position, 3, 10);

        rb = new RigidBody(position, velocity, stubArena, boxCollider);
    }

    @Test
    void testMove1() {
        //There is no collisions - with positive velocity
        Mockito.when(stubArena.detectCollision(Mockito.any(BoxCollider.class))).thenReturn(new ArrayList<>());
        Vector2d newVelocity = new Vector2d(5, 5);
        rb.setVelocity(newVelocity);

        rb.move();
        Assertions.assertEquals(rb.getPosition(), new Vector2d(10, 10));
    }

    @Test
    void testMove2() {
        //There is no collisions - with negative velocity
        Mockito.when(stubArena.detectCollision(Mockito.any(BoxCollider.class))).thenReturn(new ArrayList<>());
        Vector2d newVelocity = new Vector2d(-5, -5);
        rb.setVelocity(newVelocity);

        rb.move();
        Assertions.assertEquals(rb.getPosition(), new Vector2d(0, 0));
    }

    @Test
    void testMove3() {
        //Moving to the left and there is a wall
        Vector2d wallPosition = new Vector2d(7, 5);
        List<BoxCollider> colliders = new ArrayList<>();
        colliders.add(new BoxCollider(wallPosition, 5, 10));

        Mockito.when(stubArena.detectCollision(Mockito.any(BoxCollider.class))).thenReturn(colliders);
        Vector2d newVelocity = new Vector2d(3, 0);
        rb.setVelocity(newVelocity);

        rb.move();
        Assertions.assertEquals(rb.getPosition(), new Vector2d(7, 5));
    }
}

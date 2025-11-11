package com.framework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBoxCollider {
    BoxCollider boxCollider;

    @BeforeEach
    void setUp() {
        Vector2d position = new Vector2d(5, 5);
        boxCollider = new BoxCollider(position, 10, 5);
    }

    @Test
    void TestIsColliding1() {
        //Check when there is no collision
        Vector2d position = new Vector2d(0, 0);
        Assertions.assertFalse(boxCollider.isColliding(new BoxCollider(position, 1, 1)));
    }

    @Test
    void TestIsColliding2() {
        //Check when topLeft is colliding
        Vector2d position = new Vector2d(2, 2);
        Assertions.assertTrue(boxCollider.isColliding(new BoxCollider(position, 5, 5)));
    }

    @Test
    void TestIsColliding3() {
        //Check when botRight is colliding
        Vector2d position = new Vector2d(7, 7);
        Assertions.assertTrue(boxCollider.isColliding(new BoxCollider(position, 10, 10)));
    }

    @Test
    void TestIsColliding4() {
        //Check when is inside another box
        Vector2d position = new Vector2d(2, 2);
        Assertions.assertTrue(boxCollider.isColliding(new BoxCollider(position, 20, 20)));
    }

    @Test
    void TestIsColliding5() {
        //Check when is only touching the left limit
        Vector2d position = new Vector2d(2, 2);
        Assertions.assertFalse(boxCollider.isColliding(new BoxCollider(position, 3, 20)));
    }

    @Test
    void TestIsColliding6() {
        //Check when is only touching the right limit
        Vector2d position = new Vector2d(15, 2);
        Assertions.assertFalse(boxCollider.isColliding(new BoxCollider(position, 3, 20)));
    }

    @Test
    void TestIsColliding7() {
        //Check when is only touching the top limit
        Vector2d position = new Vector2d(2, 2);
        Assertions.assertFalse(boxCollider.isColliding(new BoxCollider(position, 20, 3)));
    }

    @Test
    void TestIsColliding8() {
        //Check when is only touching the bottom limit
        Vector2d position = new Vector2d(2, 10);
        Assertions.assertFalse(boxCollider.isColliding(new BoxCollider(position, 20, 5)));
    }
}

package com.aor.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestVector2d {
    Vector2d v1;
    @BeforeEach
    void setup() {
        v1 = new Vector2d(1, 1);
    }

    @Test
    void testEquals() {
        Vector2d v2 = new Vector2d(1, 1);

        Assertions.assertEquals(v1, v2);
    }

    @Test
    void testAddVector() {
        Vector2d v2 = new Vector2d(5, 2);

        v1.addVector(v2);
        Assertions.assertEquals(v1, new Vector2d(6, 3));
    }

    @Test
    void testScalarProduct() {
        int scalar = 5;

        v1.scalarProduct(scalar);
        Assertions.assertEquals(v1, new Vector2d(5, 5));
    }

    @Test
    void testSetters() {
        v1.setX(0);
        v1.setY(5);

        Assertions.assertTrue(v1.getX() == 0 && v1.getY() == 5);
    }


    @Test
    void testToString() {
        String s = "(1.0, 1.0)";
        Assertions.assertEquals(s, v1.toString());
    }
}

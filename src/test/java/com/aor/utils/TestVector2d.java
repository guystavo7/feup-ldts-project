package com.aor.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestVector2d {
    @Test
    void TestEquals() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(1, 1);

        Assertions.assertEquals(v1, v2);
    }

    @Test
    void TestAddVector() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(5, 2);

        v1.addVector(v2);
        Assertions.assertEquals(v1, new Vector2d(6, 3));
    }

    @Test
    void TestScalarProduct() {
        Vector2d v1 = new Vector2d(1, 2);
        int scalar = 5;

        v1.scalarProduct(scalar);
        Assertions.assertEquals(v1, new Vector2d(5, 10));
    }
}

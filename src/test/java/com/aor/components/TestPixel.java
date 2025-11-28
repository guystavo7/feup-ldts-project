package com.aor.components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPixel {
    Pixel pixel;

    @BeforeEach
    void setup() {
        //Color red (255, 0, 0)
        int r = 255 * 256 * 256;
        int g = 0;
        int b = 0;
        pixel = new Pixel(r + g + b);
    }

    @Test
    void testToString() {
        String red = "#ff0000";
        Assertions.assertEquals(red, pixel.toString());
    }
}

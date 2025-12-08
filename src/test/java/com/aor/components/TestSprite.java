package com.aor.components;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestSprite {
    Sprite sprite;
    TextGraphics graphicsStub;

    @BeforeEach
    void setup() {
        graphicsStub = Mockito.mock(TextGraphics.class);

        try {
            sprite = new Sprite(new Vector2d(0, 0), new Vector2d(0, 0), "src/main/resources/Hero/tile000.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetters() {
        Assertions.assertEquals(sprite.getHeight(), 8);
        Assertions.assertEquals(sprite.getWidth(), 8);
        Assertions.assertEquals(sprite.getPosition(), new Vector2d(0, 0));
    }

    @Test
    void testEquals() {
        try {
            Sprite s = new Sprite(new Vector2d(0, 0), new Vector2d(0, 0), "src/main/resources/Hero/tile001.png");
            Assertions.assertNotEquals(s, sprite);
            Assertions.assertEquals(sprite, sprite);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDraw() {
        Mockito.when(graphicsStub.drawImage(Mockito.any(TerminalPosition.class), Mockito.any(TextImage.class))).thenReturn(null);
        sprite.draw(graphicsStub);

        Mockito.verify(graphicsStub, Mockito.times(1)).drawImage(Mockito.any(TerminalPosition.class), Mockito.any(TextImage.class));
    }
}

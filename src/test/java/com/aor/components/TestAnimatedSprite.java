package com.aor.components;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestAnimatedSprite {

        GenericSprite spriteStub;
        AnimatedSprite animatedSprite;
        TextGraphics graphicsStub;


        @BeforeEach
        void setup() {
            graphicsStub = Mockito.mock(TextGraphics.class);
            spriteStub = Mockito.mock(Sprite.class);

            List<String> filesPaths = new ArrayList<>();
            filesPaths.add("src/main/resources/Hero/tile000.png");
            filesPaths.add("src/main/resources/Hero/tile001.png");
            filesPaths.add("src/main/resources/Hero/tile000_scaled.png");

            try {
                animatedSprite = new AnimatedSprite(new Vector2d(0, 0), filesPaths,1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        void testGetterAndFrameTransition() {
            Assertions.assertEquals(8, animatedSprite.getHeight());
            Assertions.assertEquals(8, animatedSprite.getWidth());
            Assertions.assertEquals(new Vector2d(0, 0), animatedSprite.getPosition());

            //time passes but the frame is still the same, the position is also changed

            animatedSprite.update(new Vector2d(1,1), 1f);
            Assertions.assertEquals(8, animatedSprite.getHeight());
            Assertions.assertEquals(8, animatedSprite.getWidth());
            Assertions.assertEquals(new Vector2d(1, 1), animatedSprite.getPosition());

            //frame transition
            animatedSprite.update(new Vector2d(2,2), 1f);

            Assertions.assertEquals(32, animatedSprite.getHeight());
            Assertions.assertEquals(32, animatedSprite.getWidth());
            Assertions.assertEquals(new Vector2d(2, 2), animatedSprite.getPosition());
        }

        @Test
        void testEquals() {
            try {
                AnimatedSprite as = new AnimatedSprite(new Vector2d(0, 0), new ArrayList<String>(Arrays.asList("src/main/resources/Hero/tile000.png","src/main/resources/Hero/tile000_scaled.png","src/main/resources/Hero/tile001.png")),1);
                Assertions.assertNotEquals(as, animatedSprite);
                Assertions.assertEquals(animatedSprite, animatedSprite);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        void testDraw() {
            Mockito.when(graphicsStub.drawImage(Mockito.any(TerminalPosition.class), Mockito.any(TextImage.class))).thenReturn(null);
            animatedSprite.draw(graphicsStub);
            Mockito.verify(graphicsStub, Mockito.times(1)).drawImage(Mockito.any(TerminalPosition.class), Mockito.any(TextImage.class));
        }


}

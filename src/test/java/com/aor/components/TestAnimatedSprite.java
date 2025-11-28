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

public class TestAnimatedSprite {

        Sprite spriteStub;
        AnimatedSprite animatedSprite;
        TextGraphics graphicsStub;


        @BeforeEach
        void setup() {
            graphicsStub = Mockito.mock(TextGraphics.class);
            spriteStub=Mockito.mock(Sprite.class);

            try {
                animatedSprite = new AnimatedSprite(new Vector2d(0, 0), new ArrayList<String>(Arrays.asList("src/main/resources/Hero/tile000.png","src/main/resources/Hero/tile000_scaled.png","src/main/resources/Hero/tile001.png")),1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        void testGetterAndFrameTransition() {
            Assertions.assertEquals(animatedSprite.getHeight(), 8);
            Assertions.assertEquals(animatedSprite.getWidth(), 8);
            Assertions.assertEquals(animatedSprite.getPosition(), new Vector2d(0, 0));

            //time passes but the frame is still the same, the position is also changed

            animatedSprite.update(new Vector2d(1,1), 0.5f);
            Assertions.assertEquals(animatedSprite.getHeight(), 8);
            Assertions.assertEquals(animatedSprite.getWidth(), 8);
            Assertions.assertEquals(animatedSprite.getPosition(), new Vector2d(1, 1));

            //frame transition
            animatedSprite.update(new Vector2d(2,2), 0.5f);

            Assertions.assertEquals(animatedSprite.getHeight(), 32);
            Assertions.assertEquals(animatedSprite.getWidth(), 32);
            Assertions.assertEquals(animatedSprite.getPosition(), new Vector2d(2, 2));


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

          //  Mockito.verify(graphicsStub, Mockito.times(1)).drawImage(Mockito.any(TerminalPosition.class), Mockito.any(TextImage.class));
            //consertar

          //  Mockito.when(spriteStub.draw( Mockito.any(TextGraphics.class))).thenReturn(null);

          //  Mockito.verify(spriteStub,Mockito.times(1)).draw(Mockito.any(TextGraphics.class));
        }


}

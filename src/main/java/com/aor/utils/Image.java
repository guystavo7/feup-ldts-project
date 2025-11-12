package com.aor.utils;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Image extends Node {
    //Class capable of drawing a sprite 2d

    public Image(Vector2d vector2d, String imagePath) {
        //todo - should take path and process it in a 2d array
        super(vector2d);
    }

    private void draw(TextGraphics graphics) {
        //todo - should be able to draw the image
    }

    public void update(Vector2d newPos, TextGraphics graphics) {
        super.update(newPos);
        draw(graphics);
    }
}

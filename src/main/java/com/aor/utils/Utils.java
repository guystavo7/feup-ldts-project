package com.aor.utils;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//A class that holds some useful methods
public class Utils {
    private Utils() {}

    public static TextImage loadImage(String path) throws IOException {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        TextImage pixels = new BasicTextImage(width, height);
        TextGraphics graphics = pixels.newTextGraphics();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(new Pixel(image.getRGB(x, y)).toString()));
                Vector2d pixelPos = new Vector2d(x, y);
                graphics.setCharacter(new TerminalPosition((int) pixelPos.getX(), (int) pixelPos.getY()), ' ');
            }
        }

        return pixels;
    }

    public static Vector2d vectorSum(Vector2d v1, Vector2d v2) {
        return new Vector2d(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

}

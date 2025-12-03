package com.aor.utils;

import com.aor.components.Pixel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public static Vector2d normalizedVector(Vector2d v) {
        double x = v.getX();
        double y = v.getY();

        Vector2d normalizedVector = new Vector2d(x, y);
        double module = Math.sqrt(x * x + y * y);
        normalizedVector.scalarProduct(1 / module);

        return normalizedVector;
    }

    public static Vector2d scalarProduct(Vector2d v, double k) {
        return new Vector2d(v.getX() * k, v.getY() * k);
    }

    public static double moveTowards(double current, double target, double maxDelta) {
        return Math.abs(target - current) <= maxDelta ? target : current + Math.signum(target - current) * maxDelta;
    }
}

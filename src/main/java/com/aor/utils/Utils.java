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
import java.util.ArrayList;
import java.util.List;

//A class that holds some useful methods
public class Utils {
    private Utils() {}

    public static List<List<Pixel>> XFLipPixeis(List<List<Pixel>> p){
        int height=p.size();
        int width=p.getFirst().size();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width/2; x++) {
                Pixel temp=p.get(y).get(x);
                p.get(y).set(x,p.get(y).get(width-x-1));
                p.get(y).set(width-x-1,temp);
            }
        }
        return p;
    }

    public static List<List<Pixel>> loadPixeis(String path) throws IOException {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        List<List<Pixel>> res=new ArrayList<>();

        for (int y = 0; y < height; y++) {
            res.add(new ArrayList<Pixel>());
            for (int x = 0; x < width; x++) {

                res.getLast().add(new Pixel(image.getRGB(x, y)));
            }
        }

        return res;
    }

    public static TextImage CreateTextImage(List<List<Pixel>> p){
        int width = p.getFirst().size();
        int height = p.size();

        TextImage pixels = new BasicTextImage(width, height);
        TextGraphics graphics = pixels.newTextGraphics();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel=p.get(y).get(x);

                graphics.setBackgroundColor(TextColor.Factory.fromString(pixel.toString()));
                Vector2d pixelPos = new Vector2d(x, y);
                graphics.setCharacter(new TerminalPosition((int) pixelPos.getX(), (int) pixelPos.getY()), ' ');

            }
        }
        return pixels;
    }

    public static List<List<Pixel>> loadTransparentImage(String path) throws IOException {
        return  loadPixeis(path);
    }
    public static List<List<Pixel>> loadXFlippedTransparentImage(String path) throws IOException {
        List<List<Pixel>> p=loadTransparentImage(path);
        p=XFLipPixeis(p);

        return p;
    }


    public static TextImage loadImage(String path) throws IOException {

        return CreateTextImage(loadPixeis(path));
    }

    public static TextImage loadXFlippedImage(String path) throws IOException {
        List<List<Pixel>> p=loadPixeis(path);
        XFLipPixeis(p);
        return  CreateTextImage(p);
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

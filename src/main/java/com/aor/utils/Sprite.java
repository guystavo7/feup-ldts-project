package com.aor.utils;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.*;
import java.util.*;

public class Sprite extends Node {
    private Integer width;
    private Integer height;
    private List<List<Pixel>> pixels;

    public Sprite(Vector2d position, String filePath) throws IOException {
        super(position);
        this.pixels = Utils.loadImage(filePath);
        this.width = pixels.getFirst().size();
        this.height = pixels.size();
    }

    public final void draw(TextGraphics graphics){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(getPixel(x, y).getRGBColor()));
                Vector2d pixelPos = new Vector2d(x + getPosition().getX(), y + getPosition().getY());

                graphics.setCharacter(new TerminalPosition(pixelPos.getX(), pixelPos.getY()), ' ');
            }
        }
    }

    public String toString(){
        return pixels.toString();
    }
    public Pixel getPixel(int x,int y){
        return pixels.get(y).get(x);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixels, getPosition());
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Sprite p = (Sprite) o;
        return this.hashCode() == p.hashCode();
    }
}

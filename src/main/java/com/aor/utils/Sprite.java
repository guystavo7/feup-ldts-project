package com.aor.utils;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.*;
import java.util.*;

public class Sprite extends Node {
    private Integer width;
    private Integer height;
    private TextImage pixels;

    public Sprite(Vector2d position, String filePath) throws IOException {
        super(position);
        this.pixels = Utils.loadImage(filePath);
        this.width = pixels.getSize().getColumns();
        this.height = pixels.getSize().getRows();
    }

    public final void draw(TextGraphics graphics){
        graphics.drawImage(new TerminalPosition(getPosition().getX(), getPosition().getY()), pixels);
    }

    public String toString(){
        return pixels.toString();
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

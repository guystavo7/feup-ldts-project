package com.aor.components;

import com.aor.utils.Utils;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.*;
import java.util.*;

public class Sprite extends Node implements Positionable {
    private Integer width;
    private Integer height;
    private TextImage pixels;
    private TextImage xFlippedImg;
    private TextImage normalImg;

    public Sprite(Vector2d position, String filePath) throws IOException {
        super(position);
        this.normalImg = Utils.loadImage(filePath);
        xFlippedImg=Utils.loadXFlippedImage(filePath);
        pixels=normalImg;
        this.width = pixels.getSize().getColumns();
        this.height = pixels.getSize().getRows();
    }

    public void draw(TextGraphics graphics){
        graphics.drawImage(new TerminalPosition((int) getPosition().getX(), (int) getPosition().getY()), pixels);
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void xFlip(){
        pixels=xFlippedImg;
    }

    public void normal(){
        pixels=normalImg;
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

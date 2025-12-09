package com.aor.components;

import java.io.IOException;
import java.util.Objects;

import com.aor.utils.Utils;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;

public class Sprite implements Positionable,GenericSprite {
    private Vector2d position;
    private Integer width;
    private Integer height;
    private TextImage pixels;
    private TextImage xFlippedImg;
    private TextImage normalImg;

    public Sprite(Vector2d position, String filePath) throws IOException {
        this.position=position;
        this.normalImg = Utils.loadImage(filePath);
        xFlippedImg=Utils.loadXFlippedImage(filePath);
        pixels=normalImg;
        this.width = pixels.getSize().getColumns();
        this.height = pixels.getSize().getRows();
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2d pos) {
        position=pos;
    }

    public GenericSprite newSprite(Vector2d position, String filePath) throws IOException {
        return new Sprite(position,filePath);
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

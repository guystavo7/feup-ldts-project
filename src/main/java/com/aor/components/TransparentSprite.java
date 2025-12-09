package com.aor.components;

import com.aor.utils.Utils;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TransparentSprite implements  GenericSprite,Positionable {
    private Vector2d position;
    private Integer width;
    private Integer height;
    private List<List<Pixel>> pixels;
    private List<List<Pixel>> xFlippedImg;
    private List<List<Pixel>> normalImg;

    public TransparentSprite(Vector2d position, String filePath) throws IOException {
        this.position=position;
        this.normalImg = Utils.loadTransparentImage(filePath);
        xFlippedImg=Utils.loadXFlippedTransparentImage(filePath);
        pixels=normalImg;
        this.width = pixels.getFirst().size();
        this.height = pixels.size();
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2d pos) {
        position=pos;
    }

    public GenericSprite newSprite(Vector2d position, String filePath)  {
        try {
            return new TransparentSprite(position,filePath);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void draw(TextGraphics graphics){

        for(int y=0;y<pixels.size();y++){
            for(int x=0;x<pixels.getFirst().size();x++){
                Pixel p= pixels.get(y).get(x);
                if(p.getAlpha()!= 0){
                    Vector2d pixelPos = new Vector2d(x, y).addVector(getPosition());
                    graphics.setBackgroundColor(TextColor.Factory.fromString(p.toString()));
                    graphics.setCharacter(new TerminalPosition((int) pixelPos.getX(), (int) pixelPos.getY()), ' ');

                }
            }
        }
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

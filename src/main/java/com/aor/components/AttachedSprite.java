package com.aor.components;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.text.Position;
import java.io.IOException;

public class AttachedSprite extends Sprite {
    int maxXdist,maxYdist;
    Vector2d relative_pos;
    Positionable reference;

    public AttachedSprite(Vector2d position, String filePath, Positionable reference,Vector2d relative_pos,int maxXdist,int maxYdist) throws IOException {
        super(position,filePath);
        this.maxXdist=maxXdist;
        this.maxYdist=maxYdist;
        this.relative_pos=relative_pos;
        this.reference=reference;
    }
    public void draw(TextGraphics graphics){
        super.setPosition(new Vector2d(allignedX(),allignedY()));
        super.draw(graphics);
    }

    private float allignedX(){
        float refX=reference.getPosition().getX()+relative_pos.getX();
        float actualRelX=super.getPosition().getX();
        if(Math.abs((actualRelX-refX))<=maxXdist){
            return super.getPosition().getX();
        }
        if(actualRelX<refX){
            return refX-maxXdist;
        }
        else {
            return refX+maxXdist;
        }
    }

    private float allignedY(){
        float refY=reference.getPosition().getY()+relative_pos.getY();
        float actualRelY=super.getPosition().getY();
        if(Math.abs((actualRelY-refY))<=maxYdist){
            return super.getPosition().getY();
        }
        if(actualRelY<refY){
            return refY-maxYdist;
        }
        else {
            return refY+maxYdist;
        }
    }
}

package com.aor.components;

import java.io.IOException;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class AttachedSprite  implements Positionable, GenericSprite {
    GenericSprite sprite;
    int maxXdist,maxYdist;
    Vector2d relative_pos;
    Positionable reference;

    public AttachedSprite(Sprite sprite, Positionable reference,Vector2d relative_pos,int maxXdist,int maxYdist) throws IOException {
        this.sprite=sprite;
        this.maxXdist=maxXdist;
        this.maxYdist=maxYdist;
        this.relative_pos=relative_pos;
        this.reference=reference;
    }
    public void draw(TextGraphics graphics){
        sprite.setPosition(new Vector2d(allignedX(),allignedY()));
        sprite.draw(graphics);
    }

    private double allignedX(){
        double refX=reference.getPosition().getX()+relative_pos.getX();
        double actualRelX=sprite.getPosition().getX();
        if(Math.abs((actualRelX-refX))<=maxXdist){
            return sprite.getPosition().getX();
        }
        if(actualRelX<refX){
            return refX-maxXdist;
        }
        else {
            return refX+maxXdist;
        }
    }

    private double allignedY(){
        double refY=reference.getPosition().getY()+relative_pos.getY();
        double actualRelY=sprite.getPosition().getY();

        if(Math.abs((actualRelY-refY))<=maxYdist){
            return sprite.getPosition().getY();
        }
        if(actualRelY<refY){
            return refY-maxYdist;
        }
        else {
            return refY+maxYdist;
        }
    }

    public Vector2d getPosition(){
        return sprite.getPosition();
    }



    public void setPosition(Vector2d pos){
        sprite.setPosition(pos);
    }



    public int getWidth(){
        return sprite.getWidth();
    }
    public int getHeight(){
        return sprite.getHeight();
    }

    @Override
    public void xFlip() {
        sprite.xFlip();
    }

    @Override
    public void normal() {
        sprite.normal();
    }
}

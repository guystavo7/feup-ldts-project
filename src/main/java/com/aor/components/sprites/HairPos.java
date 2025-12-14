package com.aor.components.sprites;

import java.io.IOException;

import com.aor.components.Node;
import com.aor.components.Positionable;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HairPos extends Node implements Positionable {
    Positionable chainedNode;
    double velocity;
    Vector2d relTarget;
    Vector2d xLimits;
    Vector2d yLimits;

    public HairPos(Vector2d pos,Vector2d offset,Positionable chainedNode,Vector2d relTarget,Vector2d xLimits,Vector2d yLimits,double velocity) throws IOException {
        super(pos,offset);
        this.chainedNode=chainedNode;
        this.relTarget=relTarget;
        this.velocity=velocity;
        this.xLimits=xLimits;
        this.yLimits=yLimits;

    }


    public void update(double delta){
        move(delta);
        setPosition(new Vector2d(allignedX(),allignedY()));
    }

    private void move(double delta){
        Vector2d pos=new Vector2d(super.getPosition() );
        pos.addVector(super.getOffset());
        pos.subVector(Utils.vectorSum(chainedNode.getPosition(),relTarget));
        double module=pos.module();
        pos.normalize();
        pos.scalarProduct(-1*velocity*delta);
        pos.addVector(super.getPosition());
        //pos.addVector(super.getOffset());
        if(!(module <1)) {
            super.setPosition(pos);
        }
    }

    private double allignedX(){
        double refX=chainedNode.getPosition().getX()+getOffset().getX();
        double actualRelX=getPosition().getX();
        double delta=(actualRelX-refX);
        //<>
        if(delta<=xLimits.getY() && delta>=xLimits.getX() ){
            return getPosition().getX();
        }
        if(actualRelX<refX){
            return refX+xLimits.getX();
        }
        else {
            return refX+xLimits.getY();
        }
    }

    private double allignedY(){
        double refY=chainedNode.getPosition().getY()+getOffset().getY();
        double actualRelY=getPosition().getY();

        double delta=(actualRelY-refY);
        //<>
        if(delta<=yLimits.getY() && delta>=yLimits.getX() ){
            return getPosition().getY();
        }
        if(actualRelY<refY){
            return refY+yLimits.getX();
        }
        else {
            return refY+yLimits.getY();
        }
    }


    @Override
    public void update() {

    }
}

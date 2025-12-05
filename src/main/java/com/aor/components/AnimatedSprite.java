package com.aor.components;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.SameTextGUIThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TO DO : generic sprite <>
public class AnimatedSprite extends Node {

    private List<GenericSprite> frames = new ArrayList<>();
    private float currentFrame;
    private float frameSpeed;

    public AnimatedSprite(Vector2d position, List<String> imgPaths, float frameSpeed,GenericSpriteFactory factory) throws IOException {
        super(position);
        System.out.println("colool");
        System.out.println(factory.getClass());

        for(String ip : imgPaths){
            frames.add(factory.newSprite(position,ip));
        }

        currentFrame = 0;
        this.frameSpeed = frameSpeed;
    }

    public final void draw(TextGraphics graphics){
        frames.get((int) currentFrame).draw(graphics);
    }
    public void xFlip(){
        for (GenericSprite f:frames){
            f.xFlip();
        }
    }
    public void normal(){
        for (GenericSprite f:frames){
            f.normal();
        }
    }
    public void update(Vector2d newPos,float timeDelta){
        currentFrame += frameSpeed * timeDelta;
        if(currentFrame > frames.size()) currentFrame = 0;

        super.update(newPos);
        frames.get((int) currentFrame).setPosition(newPos);
    }



    public int getWidth(){
        return frames.get((int) currentFrame).getWidth();
    }
    public int getHeight(){
        return frames.get((int) currentFrame).getHeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, getPosition());
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        AnimatedSprite p = (AnimatedSprite) o;
        return this.hashCode() == p.hashCode();
    }
}

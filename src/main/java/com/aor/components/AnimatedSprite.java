package com.aor.components;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimatedSprite extends Node {
    private List<Sprite> frames = new ArrayList<>();
    private double currentFrame;
    private double frameSpeed;

    public AnimatedSprite(Vector2d position, List<String> imgPaths, double frameSpeed) throws IOException {
        super(position);
        for(String ip : imgPaths){
            frames.add(new Sprite(position, ip));
        }

        currentFrame = 0;
        this.frameSpeed = frameSpeed;
    }

    public final void draw(TextGraphics graphics){
        frames.get((int) currentFrame).draw(graphics);
    }

    public void update(Vector2d newPos, double timeDelta){
        currentFrame += frameSpeed * timeDelta;
        if(currentFrame > frames.size()) currentFrame = 0;

        super.update(newPos);
        frames.get((int) currentFrame).update(newPos);
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

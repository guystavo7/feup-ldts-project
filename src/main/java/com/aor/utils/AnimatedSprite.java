package com.aor.utils;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AnimatedSprite extends Node {
    private List<Sprite> frames;
    private int curFrame=0;
    private float switchDelta;//tempo de tela de cada frame em millisegundos
    private float counter=0;
    private float animationDuration;
    AnimatedSprite(Vector2d pos,List<String> imgPaths,float switchDelta) throws IOException {
        super(pos);
        assert (imgPaths.size()!=0);
        for(String ip:imgPaths){
            frames.add(new Sprite(pos,ip));
        }
        this.switchDelta=switchDelta;
        this.animationDuration=frames.size()*switchDelta;
    }

    public final void draw(TextGraphics graphics){
        frames.get(curFrame).draw(graphics);
    }

    public void update(Vector2d newPos,float timeDelta){
        counter+=timeDelta*1000;
        if(counter>animationDuration){
            counter=0;
        }
        else {
            curFrame=(int)((counter)/switchDelta)+1;
        }


        frames.get(curFrame).update(newPos);
        super.update(newPos);
    }


    public int getWidth(){
        return frames.get(curFrame).getWidth();
    }
    public int getHeight(){
        return frames.get(curFrame).getHeight();
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

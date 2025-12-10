package com.aor.components;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;


public interface GenericSprite {
    public void draw(TextGraphics graphics);
    public int getWidth();
    public int getHeight();
    public void xFlip();
    public void normal();

    public Vector2d getPosition();
    public void setPosition( Vector2d pos);
    public int hashCode();

    public boolean equals(Object o);
}

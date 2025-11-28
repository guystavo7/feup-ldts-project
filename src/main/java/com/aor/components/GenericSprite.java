package com.aor.components;

import com.aor.utils.Utils;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.IOException;
import java.util.Objects;

public interface GenericSprite  {

    public  void draw(TextGraphics graphics);

    public int getWidth();
    public int getHeight();

    public int hashCode();

    public boolean equals(Object o);

    public void update(Vector2d newPos);

    //Getters and Setters
    public Vector2d getPosition();

    public void setPosition(Vector2d position);
}

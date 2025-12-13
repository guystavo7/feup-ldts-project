package com.aor.components.sprites;

import com.aor.components.Positionable;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class HairComponent implements Positionable {
    TransparentSprite sprite;
    HairPos pos;
    public HairComponent(Vector2d pos, Vector2d relPos, Positionable chainedComponent, Vector2d relTarget, Vector2d xLimits,Vector2d yLimits, double velocity, String filePath) throws IOException {
        sprite=new TransparentSprite(pos,filePath);
        this.pos=new HairPos(pos,relPos,chainedComponent, relTarget,xLimits,yLimits,velocity);

    }
    public void update( double delta){
        pos.update(delta);
    }

    public void draw(TextGraphics graphics){
        sprite.update(pos.getPosition());
        sprite.draw(graphics);
    }

    @Override
    public Vector2d getPosition() {
        return pos.getPosition();
    }
}

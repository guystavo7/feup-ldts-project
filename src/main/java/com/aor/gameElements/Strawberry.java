package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Strawberry extends Tile {
    boolean active=true;
    public Strawberry(Vector2d pos) throws IOException {
        super(pos, GameData.tileImgsPath.get("strawberry"));
        super.getBoxCollider().setTag(GameData.strawberryTag);
    }

    public void  kill(){
        active=false;
    }

    @Override
    public void update(TextGraphics graphics, double delta){
        active=!super.getBoxCollider().getCollidedOnce();
        if(active){
            super.update(graphics,delta);
        }
    }

    public boolean isActive() {
        return active;
    }
}

package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class GroundVoidBlock extends EmptyBlock {


    public GroundVoidBlock(Vector2d pos) throws IOException {
        super(pos,GameData.VOID_TILE_TAG);
        super.getBoxCollider().setPosition(assertPos(pos));
    }

    private Vector2d assertPos(Vector2d pos){
        pos.setY(pos.getY()+15);
        return pos;
    }
}


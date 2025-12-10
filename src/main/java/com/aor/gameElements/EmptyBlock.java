package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.utils.Vector2d;
import com.aor.tile.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class EmptyBlock extends Tile{
    public EmptyBlock(Vector2d pos,String tag) throws IOException {
        super(pos, GameData.tileImgsPath.get("spike"));
        super.getBoxCollider().setTag(tag);
    }
    @Override
    public void update(TextGraphics graphics, double delta){

    }
}

package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class GroundVoidBlock extends EmptyBlock {
    public GroundVoidBlock(Vector2d position) throws IOException {
        super(position, new Vector2d(0, 8), 8, 8, GameData.VOID_TILE_TAG);
    }
}


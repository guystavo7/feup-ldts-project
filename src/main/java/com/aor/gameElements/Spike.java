package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Spike extends Tile {

    public Spike(Vector2d pos) throws IOException {
        super(pos, GameData.tileImgsPath.get("spike"));
        super.getBoxCollider().setTag(GameData.spikeTag);
    }


}

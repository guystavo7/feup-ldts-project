package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class GroundedSpike extends Tile {

    public GroundedSpike(Vector2d pos) throws IOException {
        super(pos, GameData.tileImgsPath.get("spike"));
        pos.setY(pos.getY()+2);
        super.getBoxCollider().setPosition(pos);
        super.getBoxCollider().setTag(GameData.SPIKE_TAG);
    }


}

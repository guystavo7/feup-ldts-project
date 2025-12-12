package com.aor.GameLoader;

import com.aor.game.GameData;
import com.aor.gameElements.GroundedSpike;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class GroundedSpikeLoader implements GameElementLoader {
    @Override
    public GroundedSpike Parse(String data, Vector2d pos) throws IOException {// format
        return new GroundedSpike(pos);
    }
}

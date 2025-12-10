package com.aor.GameLoader;

import com.aor.tile.*;
import com.aor.game.GameData;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class TileLoader implements GameElementLoader {
    @Override
    public Tile Parse(String data, Vector2d pos) throws IOException {// format
        return new Tile(pos, GameData.tileImgsPath.get(data));
    }
}

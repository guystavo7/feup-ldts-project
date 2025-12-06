package com.aor.GameLoader;

import com.aor.Tiles.Tile;
import com.aor.game.GameDataFeature;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class TileLoader implements GameElementLoader{
    @Override
    public Tile Parse(String data, Vector2d pos) throws IOException {// format
        return new Tile(pos, GameDataFeature.tileImgsPath.get(Integer.parseInt(data)));
    }
}

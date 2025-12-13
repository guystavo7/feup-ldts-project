package com.aor.gameElements;

import com.aor.tile.Tile;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class BackgroundBlock  extends Tile {
    public BackgroundBlock(Vector2d position, String path) throws IOException {
        super(position, path, " ");
    }
}

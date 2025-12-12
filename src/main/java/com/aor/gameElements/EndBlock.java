package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class EndBlock extends EmptyBlock {
    public EndBlock(Vector2d pos) throws IOException {
        super(pos, GameData.END_BLOCK_TAG);
    }
}

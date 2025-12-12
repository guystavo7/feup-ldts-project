package com.aor.GameLoader;

import com.aor.gameElements.GroundVoidBlock;
import com.aor.gameElements.GroundedSpike;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class GroundedVoidBlockLoader implements GameElementLoader{
    @Override
    public GroundVoidBlock Parse(String data, Vector2d pos) throws IOException {
        return new GroundVoidBlock(pos);
    }
}

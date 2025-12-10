package com.aor.GameLoader;

import com.aor.gameElements.EmptyBlock;
import com.aor.gameElements.EndBlock;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class EndBlockLoader implements GameElementLoader {

    @Override
    public EndBlock Parse(String data, Vector2d pos) throws IOException {
        return new EndBlock(pos);
    }
}

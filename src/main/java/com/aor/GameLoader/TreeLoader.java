package com.aor.GameLoader;


import com.aor.game.GameData;
import com.aor.gameElements.BackgroundBlock;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class TreeLoader implements GameElementLoader {
    @Override
    public BackgroundBlock Parse(String data, Vector2d position) throws IOException {
        String type = "A" + data;
        return new BackgroundBlock(position, GameData.tileImgsPath.get(type));
    }
}

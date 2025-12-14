package com.aor.GameLoader;

import com.aor.game.GameData;
import com.aor.gameElements.BackgroundBlock;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class FlowerLoader implements GameElementLoader {
    @Override
    public BackgroundBlock Parse(String data, Vector2d position) throws IOException {
        return new BackgroundBlock(position, new Vector2d(1, 1), 5, 7, GameData.tileImgsPath.get("flower"));
    }
}
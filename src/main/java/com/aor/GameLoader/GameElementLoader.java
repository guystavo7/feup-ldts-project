package com.aor.GameLoader;

import com.aor.utils.Vector2d;

import java.io.IOException;

public interface GameElementLoader {
    GameElement Parse(String data, Vector2d pos) throws IOException;
}

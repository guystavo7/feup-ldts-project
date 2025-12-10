package com.aor.GameLoader;

import com.aor.gameElements.Strawberry;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class StraberryLoader implements GameElementLoader{
    @Override
    public Strawberry Parse(String data, Vector2d pos) throws IOException {
        return new Strawberry(pos);
    }
}

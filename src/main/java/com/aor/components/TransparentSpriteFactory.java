package com.aor.components;

import com.aor.utils.Vector2d;

import java.io.IOException;

public class TransparentSpriteFactory implements GenericSpriteFactory{

    @Override
    public GenericSprite newSprite(Vector2d position, String filePath) throws IOException {
        return new TransparentSprite(position,filePath);
    }
}

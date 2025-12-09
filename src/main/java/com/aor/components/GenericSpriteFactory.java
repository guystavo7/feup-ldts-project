package com.aor.components;

import com.aor.utils.Vector2d;

import java.io.IOException;

public interface  GenericSpriteFactory {
    public GenericSprite  newSprite(Vector2d position, String filePath) throws IOException;
}

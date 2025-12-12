package com.aor.tile;

import com.aor.components.sprites.SolidSprite;
import com.aor.mvc.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class TileView implements GenericView {
    private TileModel tileModel;
    private SolidSprite sprite;

    public TileView(TileModel tileModel) throws IOException {
        sprite = new SolidSprite(tileModel.getPosition(), tileModel.getFilePath());
    }

    @Override
    public void update(TextGraphics graphics, double delta) {
        sprite.draw(graphics);
    }
}
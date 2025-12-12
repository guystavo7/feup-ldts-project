package com.aor.tile;

import com.aor.components.sprites.SolidSprite;
import com.aor.mvc.GenericModel;
import com.aor.mvc.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class TileView implements GenericView {
    private TileModel tileModel;
    private SolidSprite sprite;

    public TileView(GenericModel tileModel) throws IOException {
        this.tileModel = (TileModel) tileModel;
        sprite = new SolidSprite(this.tileModel.getPosition(), this.tileModel.getFilePath());
    }

    @Override
    public void update(TextGraphics graphics, double delta) {
        sprite.draw(graphics);
    }
}
package com.aor.tiles;

import com.aor.components.Sprite;
import com.aor.mvc.GenericView;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class TileView implements GenericView {
    private TileModel tileModel;
    private Sprite sprite;

    public TileView(TileModel tileModel) throws IOException {
        sprite=new Sprite(tileModel.getPosition(), tileModel.getFilePath());
    }
    @Override
    public void update(TextGraphics graphics, double delta) {
        sprite.draw(graphics);
    }
}

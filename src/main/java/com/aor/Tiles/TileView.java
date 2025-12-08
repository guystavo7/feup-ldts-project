package com.aor.Tiles;

import com.aor.components.Sprite;
import com.aor.mvc.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.IIOException;
import java.io.IOException;

public class TileView implements GenericView {
    private TileModel tileModel;
    private Sprite sprite;

    public TileView(TileModel tileModel) throws IOException {
        sprite=new Sprite(tileModel.getPosition(), new Vector2d(0, 0), tileModel.getFilePath());
    }
    @Override
    public void update(TextGraphics graphics, double delta) {
        sprite.draw(graphics);
    }
}

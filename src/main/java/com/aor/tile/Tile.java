package com.aor.tile;

import java.io.IOException;

import com.aor.elements.GameElement;
import com.aor.components.BoxCollider;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tile implements GameElement {
    TileView tileView;
    TileModel tileModel;
    TileController tileController;

    public Tile(Vector2d position, Vector2d offset, int width, int height, String filePath, String tag) throws IOException {
        tileModel = new TileModel(position, offset, width, height, filePath, tag);
        tileView = new TileView(tileModel);
        tileController = new TileController(tileModel, this.tileModel.getTag());
    }

    public Tile(Vector2d position, String filePath, String tag) throws IOException {
        tileModel = new TileModel(position, filePath, tag);
        tileView = new TileView(tileModel);
        tileController = new TileController(tileModel, this.tileModel.getTag());
    }

    public void update(TextGraphics graphics, double delta) {
        tileController.update(delta);
        tileView.update(graphics, delta);
    }

    public BoxCollider getBoxCollider() {
        return tileController.getBoxCollider();
    }

    public void setSpeed(Vector2d speed) {
        tileController.setSpeed(speed);
    }
}

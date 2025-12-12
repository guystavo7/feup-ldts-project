package com.aor.tile;

import com.aor.components.BoxCollider;
import com.aor.game.GameData;
import com.aor.mvc.GenericController;
import com.aor.mvc.GenericModel;

public class TileController implements GenericController {
    BoxCollider boxCollider;
    TileModel tileModel;

    public TileController(GenericModel tileModel){
        this.tileModel = (TileModel) tileModel;
        this.boxCollider = new BoxCollider(this.tileModel.getPosition(), this.tileModel.getWidth(), this.tileModel.getHeight(), GameData.TILE_TAG);
    }

    @Override
    public void update(double delta) {}

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

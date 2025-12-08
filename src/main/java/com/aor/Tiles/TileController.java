package com.aor.Tiles;

import com.aor.components.BoxCollider;
import com.aor.mvc.GenericController;
import com.aor.utils.Vector2d;

public class TileController implements GenericController {
    BoxCollider boxCollider;
    TileModel tileModel;

    public TileController(TileModel tileModel){
        boxCollider=new BoxCollider(tileModel.getPosition(), Vector2d.VECTORZERO, tileModel.getWidth(), tileModel.getHeight(), "tile");
    }

    @Override
    public void update(double delta) {

    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

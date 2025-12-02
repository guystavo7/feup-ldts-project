package com.aor.Tiles;

import com.aor.components.BoxCollider;
import com.aor.mvc.GenericController;

public class TileController implements GenericController {
    BoxCollider boxCollider;
    TileModel tileModel;
    TileController(TileModel tileModel){
        boxCollider=new BoxCollider(tileModel.getPosition(),tileModel.getWidth(), tileModel.getHeight());
    }

    @Override
    public void update(float delta) {

    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

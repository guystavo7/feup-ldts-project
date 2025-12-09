package com.aor.tile;

import com.aor.components.BoxCollider;
import com.aor.game.GameData;
import com.aor.mvc.GenericController;
import com.aor.utils.Vector2d;

public class TileController implements GenericController {
    BoxCollider boxCollider;
    TileModel tileModel;

    public TileController(TileModel tileModel){
        boxCollider=new BoxCollider(tileModel.getPosition(), new Vector2d(0, 0), tileModel.getWidth(), tileModel.getHeight(), GameData.getInstance().getTileTag());
    }

    @Override
    public void update(double delta) {

    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

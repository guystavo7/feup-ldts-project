package com.aor.Tiles;

import com.aor.GameLoader.GameElement;
import com.aor.components.BoxCollider;
import com.aor.mvc.GenericController;
import com.aor.mvc.GenericModel;
import com.aor.mvc.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.text.View;
import java.io.IOException;

public class Tile implements GameElement {
    TileView tileView;
    TileModel tileModel;
    TileController tileController;

    public Tile(Vector2d pos,String filePath) throws IOException {
        tileModel=new TileModel(pos,filePath);
        tileView=new TileView(tileModel);
        tileController=new TileController(tileModel);
    }
    public static Tile TileFrom16x16Grid(int gridx,int gridy,String filePath) throws IOException {
        return new Tile(new Vector2d(gridx*8,gridy*8),filePath);
    }
    public void update(TextGraphics graphics, float delta) {
        tileController.update(delta);
        tileView.update(graphics, delta);
    }

    public BoxCollider getBoxCollider(){
        return tileController.getBoxCollider();
    }

}

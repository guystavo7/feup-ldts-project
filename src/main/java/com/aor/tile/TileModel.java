package com.aor.tile;

import com.aor.game.GameData;
import com.aor.mvc.GenericModel;
import com.aor.utils.Vector2d;



public class TileModel implements GenericModel {
    int width= GameData.tileWidth,height= GameData.tileHeight;
    Vector2d pos;
    String filePath;
    public TileModel(Vector2d pos,String filePath){
        this.pos=pos;
        this.filePath=filePath;
    }

    public String getFilePath() {
        return filePath;
    }
    public Vector2d getPosition(){
        return pos;
    }
    public int getWidth(){return width;}

    public int getHeight(){return height;}
}

package com.aor.tile;

import com.aor.game.GameData;
import com.aor.mvc.GenericModel;
import com.aor.utils.Vector2d;



public class TileModel implements GenericModel {
    private int width;
    private int height;
    private Vector2d position;
    private String filePath;

    public TileModel(Vector2d position, String filePath){
        this.position = position;
        this.filePath = filePath;
        width = GameData.TILE_WIDTH;
        height = GameData.TILE_HEIGHT;
    }

    public String getFilePath() {
        return filePath;
    }
    public Vector2d getPosition(){
        return position;
    }
    public int getWidth(){return width;}

    public int getHeight(){return height;}
}

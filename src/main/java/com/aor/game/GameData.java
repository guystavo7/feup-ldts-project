package com.aor.game;

import java.util.*;

public class GameData {
    public final int SCREEN_WIDTH = 128;
    public final int SCREEN_HEIGHT = 128;

    // static varibles
    public static int tileWidth = 8;
    public static int tileHeight=8;

    private static GameData instance;
    public static Map<String ,String> tileImgsPath = tileImgPathLoader();

    public String playerTag = "player";
    public String tileTag = "tile";
    public String allColliders = "all";

    public double maxHorizontalVelocity = 1d;
    public double maxVerticalVelocity = 1d;
    public double maxVerticalVelocityFalling = 1.5d;

    public double groundFriction = 15d;
    public double airFriction = 8d;
    public double dashFriction = 12d;

    public double horizontalAcceleration = 10d;
    public double gravityAcceleration = 5d;
    public double jumpForce = -2d;
    public double dashForce = 3.4d;

    public double jumpBufferTime = 0.15d;
    public double coyoteTime = 0.15d;
    public double dashTime = 0.25d;
    public double wallJumpTime = 0.15d;


    private static Map<String ,String> tileImgPathLoader(){
        //src/main/resources/Tiles/tile000.png

        List<String> l = Arrays.asList("00","01","02","03", "04", "05", "06", "08", "13");
        Map<String ,String> res=new HashMap<>();
        for(String i : l){
            res.put(i,"src/main/resources/Tiles/tile0" + i + ".png");

        }
        return res;
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }
}

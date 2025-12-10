package com.aor.game;

import java.util.*;

public class GameData {
    public int SCREEN_WIDTH = 128;
    public int SCREEN_HEIGHT = 128;

    // static varibles
    public static int tileWidth = 8;
    public static int tileHeight=8;

    private static GameData instance;
    public static Map<String ,String> tileImgsPath = tileImgPathLoader();

    private String playerTag = "player";
    private String tileTag = "tile";
    private String allColliders = "all";

    private int collectedstrawberries = 0;
    private int totalstrawberries = 0;

    public double maxHorizontalVelocity = 1d;
    public double maxVerticalVelocity = 1d;
    public double maxVerticalVelocityFalling = 1.5d;

    public double groundFriction = 15d;
    public double airFriction = 8d;
    public double dashFriction = 10d;

    public double horizontalAcceleration = 10d;
    public double gravityAcceleration = 5d;
    public double jumpForce = -2d;
    public double dashForce = 3.4d;

    public double jumpBufferTime = 0.15d;
    public double coyoteTime = 0.15d;
    public double dashTime = 0.30d;
    public double wallJumpTime = 0.15d;
    //Level data
    private int currentlevel;
    private int totallevels;
    private List<Integer> completedlevels = new ArrayList<>();

    //Rendering settings
    private int windowwidth;
    private int windowheight;

    private static Map<String ,String> tileImgPathLoader(){
        //src/main/resources/Tiles/tile000.png

        List<String> l = Arrays.asList("00","01","02","03", "04", "05", "06", "08", "13");
        Map<String ,String> res=new HashMap<>();
        for(String i : l){
            res.put(i,"src/main/resources/Tiles/tile0" + i + ".png");

        }
        return res;
    }

    //Input settings
    private boolean iskeyboardInverted = false;
    private float inputSensivity;

    private GameData() {
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public int getCollectedstrawberries() {return collectedstrawberries;}
    public void setCollectedstrawberries(int count) {this.collectedstrawberries = count;}


    public int getTotalstrawberries() {return totalstrawberries;}
    public void setTotalstrawberries(int count) {this.totalstrawberries = count;}
    public int getWidth() {return windowwidth;}
    public void setWidth(int width) {this.windowwidth = width;}
    public int getHeight() {return windowheight;}
    public void setHeight(int height) {this.windowheight = height;}
    public boolean isLevelCompleted(int level) {
        return completedlevels.contains(level);
    }



    public String getPlayerTag() {
        return playerTag;
    }

    public String getTileTag() {
        return tileTag;
    }

    public String getAllColliders() {
        return allColliders;
    }
}

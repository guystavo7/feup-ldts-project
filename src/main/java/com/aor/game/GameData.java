package com.aor.game;

import java.util.*;

public class GameData {
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

    private double maxHorizontalVelocity = 1.5d;
    private double maxVerticalVelocity = 2;

    private double groundFriction = 15d;
    private double airFriction = 8d;
    private double dashFriction = 10d;

    private double horizontalAcceleration = 85d;
    private double gravityAcceleration = 10d;
    private double jumpForce = -3d;

    private double jumpBufferTime = 0.1d;
    private double coyoteTime = 0.1d;
    private double dashTime = 0.25d;
    private double wallJumpTime = 0.15d;

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


    public double getMaxHorizontalVelocity() {
        return maxHorizontalVelocity;
    }

    public double getMaxVerticalVelocity() {
        return maxVerticalVelocity;
    }

    public double getHorizontalAcceleration() {
        return horizontalAcceleration;
    }

    public double getGravityAcceleration() {
        return gravityAcceleration;
    }

    public double getGroundFriction() {
        return groundFriction;
    }

    public double getAirFriction() {
        return airFriction;
    }

    public double getJumpForce() {
        return jumpForce;
    }

    public double getDashFriction() {
        return dashFriction;
    }

    public double getCoyoteTime() {
        return coyoteTime;
    }

    public double getDashTime() {
        return dashTime;
    }

    public double getJumpBufferTime() {
        return jumpBufferTime;
    }

    public double getWallJumpTime() {
        return wallJumpTime;
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

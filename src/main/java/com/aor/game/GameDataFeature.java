package com.aor.game;

import java.util.ArrayList;
import java.util.List;

public class GameDataFeature {
    // static varibles
    public static int tileWidth=8;
    public static int tileHeight=8;

    private static GameDataFeature instance;

    private int collectedstrawberries = 0;
    private int totalstrawberries = 0;

    private boolean hasDash = true;

    private double maxHorizontalVelocity = 1.5d;
    private double maxVerticalVelocity = 2;

    private double groundFriction = 15d;
    private double airFriction = 8d;
    private double dashFriction = 10d;

    private double horizontalAcceleration = 85d;
    private double gravityAcceleration = 10d;
    private double jumpForce = -4d;

    //Physics settings
    private float gravity;
    private float jumpvelocity;
    private float playerspeed;
    private float dashspeed;
    private float coyotetime;

    //Level data
    private int currentlevel;
    private int totallevels;
    private List<Integer> completedlevels = new ArrayList<>();

    //Rendering settings
    private int windowwidth;
    private int windowheight;


    //Input settings
    private boolean iskeyboardInverted = false;
    private float inputSensivity;

    private GameDataFeature() {
    }

    public static GameDataFeature getInstance() {
        if (instance == null) {
            instance = new GameDataFeature();
        }
        return instance;
    }

    public int getCollectedstrawberries() {return collectedstrawberries;}
    public void setCollectedstrawberries(int count) {this.collectedstrawberries = count;}

    public boolean hasDash() {return hasDash;}
    public void sethasDash(boolean hasDash) {this.hasDash = hasDash;}

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
}

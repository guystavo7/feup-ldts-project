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

    //Physics settings
    private float gravity;
    private float jumpvelocity;
    private float playerspeed;
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

    public int getTotalstrawberries() {return totalstrawberries;}
    public void setTotalstrawberries(int count) {this.totalstrawberries = count;}

    public int getWidth() {return windowwidth;}
    public void setWidth(int width) {this.windowwidth = width;}

    public int getHeight() {return windowheight;}
    public void setHeight(int height) {this.windowheight = height;}


}

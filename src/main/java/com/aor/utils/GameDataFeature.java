package com.aor.utils;

import java.util.ArrayList;
import java.util.List;

public class GameDataFeature {
    private static GameDataFeature instance;



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

    public int getWidth() {return windowwidth;}
    public void setWidth(int width) {this.windowwidth = width;}

    public int getHeight() {return windowheight;}
    public void setHeight(int height) {this.windowheight = height;}


}

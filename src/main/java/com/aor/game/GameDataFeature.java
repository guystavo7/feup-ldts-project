package com.aor.game;

import java.util.*;

public class GameDataFeature {
    // static varibles
    public static int tileWidth=8;
    public static int tileHeight=8;

    public static Map<Integer ,String> tileImgsPath=tileImgPathLoader();


    private static GameDataFeature instance;

    private int collectedstrawberries = 0;
    private int totalstrawberries = 0;

    private boolean hasDash = true;


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

    private static Map<Integer ,String> tileImgPathLoader(){
        List<Integer> l= Arrays.asList(26,27,28,29,30,31,32,34,46);
        Map<Integer ,String> res=new HashMap<>();
        for(Integer i:l){
            res.put(i,"src/main/resources/Hero/tile0"+i.toString()+".png");
        }
        return res;
    }
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


}

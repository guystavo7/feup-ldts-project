package com.aor.game;

import java.util.*;

public class GameData {
    public static final int SCREEN_WIDTH = 128;
    public static final int SCREEN_HEIGHT = 128;

    // static varibles
    public static final int TILE_WIDTH = 8;
    public static final int TILE_HEIGHT = 8;

    public static Map<String ,String> tileImgsPath = tileImgPathLoader();

    public static final String PLAYER_TAG = "player";
    public static final String TILE_TAG = "tile";
    public static final String ALL_COLLIDERS = "all";

    public static final double MAX_HORIZONTAL_VELOCITY = 1d;
    public static final double MAX_VERTICAL_VELOCITY = 1d;
    public static final double MAX_VERTICAL_VELOCITY_FALLING = 1.5d;

    public static final double GROUND_FRICTION = 15d;
    public static final double AIR_FRICTION = 8d;
    public static final double DASH_FRICTION = 12d;

    public static final double HORIZONTAL_ACCELERATION = 10d;
    public static final double GRAVITY_ACCELERATION = 5d;

    public static final double JUMP_FORCE = -2d;
    public static final double DASH_FORCE = 3.4d;

    public static final double JUMP_BUFFER_TIME = 0.15d;
    public static final double COYOTE_TIME = 0.15d;
    public static final double DASH_TIME = 0.25d;
    public static final double WALL_JUMP_TIME = 0.15d;


    private static Map<String ,String> tileImgPathLoader(){
        List<String> l = Arrays.asList("00","01","02","03", "04", "05", "06", "08", "13");
        Map<String ,String> res=new HashMap<>();
        for(String i : l){
            res.put(i,"src/main/resources/Tiles/tile0" + i + ".png");

        }
        return res;
    }}

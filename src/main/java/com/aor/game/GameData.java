package com.aor.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final String END_BLOCK_TAG = "end";
    public static final String STRAWBERRY_TAG = "strawberry";
    public static final String SPIKE_TAG = "spike";
    public static final String VOID_TILE_TAG = "void";

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
        Map<String ,String> res=new HashMap<>();
        for(Integer i = 0; i < 37; i++){
            String id = i.toString();
            if (i < 10) id = "0" + id;

            res.put(id, "src/main/resources/Tiles/tile0" + id + ".png");
        }

        res.put("B","src/main/resources/misc/strawberry.png");
        res.put("strawberry", "src/main/resources/misc/strawberry.png");
        res.put("spike", "src/main/resources/misc/Spike.png");

        return res;
    }}

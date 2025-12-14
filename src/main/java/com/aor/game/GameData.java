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
    public static final String INV_WALL_TAG = "inv";

    public static final double MAX_HORIZONTAL_VELOCITY = 1d;
    public static final double MAX_VERTICAL_VELOCITY = 1d;
    public static final double MAX_VERTICAL_VELOCITY_FALLING = 1.5d;

    public static final double GROUND_FRICTION = 15d;
    public static final double AIR_FRICTION = 8d;
    public static final double DASH_FRICTION = 15d;

    public static final double HORIZONTAL_ACCELERATION = 10d;
    public static final double GRAVITY_ACCELERATION = 5d;

    public static final double JUMP_FORCE = -1.8d;
    public static final double DASH_FORCE = 3.8d;
    public static final double WALL_JUMP_FORCE_HORIZONTAL = 1.5d;
    public static final double WALL_JUMP_FORCE_VERTICAL = -1.2d;

    public static final double JUMP_BUFFER_TIME = 0.15d;
    public static final double COYOTE_TIME = 0.15d;
    public static final double DASH_TIME = 0.25d;
    public static final double WALL_JUMP_TIME = 0.15d;

    public static final double STRAWBERRY_SPEED = 0.1d;
    public static final double STRAWBERRY_TIME = 0.5d;

    private static HashMap<String, String> imagePaths;

    private static Map<String ,String> tileImgPathLoader(){
        if (imagePaths != null) return imagePaths;

        imagePaths = new HashMap<>();
        for(Integer i = 0; i < 37; i++){
            String id = i.toString();
            if (i < 10) id = "0" + id;

            imagePaths.put("T" + id, "src/main/resources/Tiles/tile0" + id + ".png");
        }

        for (Integer i = 0; i < 8; i++) {
            String id = i.toString();
            if (i < 10) id = "0" + id;

            imagePaths.put("F" + id, "src/main/resources/Background/bg0" + id + ".png");
        }

        imagePaths.put("strawberry", "src/main/resources/misc/strawberry.png");
        imagePaths.put("spike", "src/main/resources/misc/Spike.png");
        imagePaths.put("flower", "src/main/resources/misc/flower.png");
        imagePaths.put("G00", "src/main/resources/misc/grass0.png");
        imagePaths.put("G01", "src/main/resources/misc/grass1.png");
        imagePaths.put("A00", "src/main/resources/Tree/tree0.png");
        imagePaths.put("A01", "src/main/resources/Tree/tree1.png");

        return imagePaths;
    }

}

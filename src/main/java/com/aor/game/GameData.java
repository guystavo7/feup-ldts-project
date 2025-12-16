package com.aor.game;

import com.aor.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameData {
    private GameData() {}

    public static final int SCREEN_WIDTH = 128;
    public static final int SCREEN_HEIGHT = 128;

    // static varibles
    public static final int TILE_WIDTH = 8;
    public static final int TILE_HEIGHT = 8;

    public static Map<String ,String> tileImgsPath = tileImgPathLoader();
    public static Map<String, File> sfxFiles = getSfxFiles();
    
    public static final String PLAYER_TAG = "player";
    public static final String TILE_TAG = "tile";
    public static final String ALL_COLLIDERS = "all";
    public static final String END_BLOCK_TAG = "end";
    public static final String STRAWBERRY_TAG = "strawberry";
    public static final String SPIKE_TAG = "spike";
    public static final String VOID_TILE_TAG = "void";
    public static final String INV_WALL_TAG = "inv";

    public static final double MAX_HORIZONTAL_VELOCITY = 50d;
    public static final double MAX_VERTICAL_VELOCITY = 80d;
    public static final double MAX_VERTICAL_VELOCITY_FALLING = 100d;

    public static final double GROUND_FRICTION = 300d;
    public static final double AIR_FRICTION = 280d;
    public static final double DASH_FRICTION = 600d;

    public static final double HORIZONTAL_ACCELERATION = 200d;
    public static final double GRAVITY_ACCELERATION = 270d;
    public static final double GRAVITY_ACCELERATION_FALLING = 350d;

    public static final double JUMP_FORCE = -110d;
    public static final double DASH_FORCE = 180d;
    public static final double WALL_JUMP_FORCE_HORIZONTAL = 90d;
    public static final double WALL_JUMP_FORCE_VERTICAL = -90d;

    public static final double JUMP_BUFFER_TIME = 0.15d;
    public static final double COYOTE_TIME = 0.15d;
    public static final double DASH_TIME = 0.25d;
    public static final double WALL_JUMP_TIME = 0.10d;

    public static final double STRAWBERRY_SPEED = 0.1d;
    public static final double STRAWBERRY_TIME = 0.5d;

    private static HashMap<String, String> imagePaths;

    private static Map<String ,String> tileImgPathLoader(){
        if (imagePaths != null) return imagePaths;

        imagePaths = new HashMap<>();
        for(Integer i = 0; i < 37; i++){
            String id = i.toString();
            if (i < 10) id = "0" + id;

            imagePaths.put("T" + id, Utils.createTempFile("Tiles/tile0" + id + ".png"));
        }

        for (Integer i = 0; i < 8; i++) {
            String id = i.toString();
            if (i < 10) id = "0" + id;

            imagePaths.put("F" + id, Utils.createTempFile("Background/bg0" + id + ".png"));
        }

        imagePaths.put(STRAWBERRY_TAG, Utils.createTempFile("Misc/strawberry.png"));
        imagePaths.put(SPIKE_TAG, Utils.createTempFile("Misc/Spike.png"));
        imagePaths.put("flower", Utils.createTempFile("Misc/flower.png"));
        imagePaths.put("G00", Utils.createTempFile("Misc/grass0.png"));
        imagePaths.put("G01", Utils.createTempFile("Misc/grass1.png"));
        imagePaths.put("A00", Utils.createTempFile("Tree/tree0.png"));
        imagePaths.put("A01", Utils.createTempFile("Tree/tree1.png"));

        return imagePaths;
    }

    private static Map<String, File> getSfxFiles() {
        if (sfxFiles != null) return sfxFiles;

        sfxFiles = new HashMap<>();
        sfxFiles.put("ost1", new File(Utils.createTempFile("Music/ost1.wav")));
        sfxFiles.put("dash", new File(Utils.createTempFile("Music/dash.wav")));
        sfxFiles.put("jump", new File(Utils.createTempFile("Music/jump.wav")));

        return sfxFiles;
    }

}

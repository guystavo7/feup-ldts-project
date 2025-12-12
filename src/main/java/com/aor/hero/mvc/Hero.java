package com.aor.hero.mvc;

import com.aor.game.Arena;
import com.aor.hero.states.*;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hero {
    private HeroView heroView;
    private HeroController heroController;
    private HeroModel heroModel;

    public Hero(Vector2d position, Arena arena) {
        heroModel = new HeroModel(position, new Vector2d(0, 0), new Vector2d(0, 0));
        HashMap<Class, List<String>> filePaths = new HashMap<>();
        HashMap<Class, List<String>> filePaths_v1 = new HashMap<>();

        //Running
        List<String> runningFiles = new ArrayList<>();
        List<String> runningFiles_v1 = new ArrayList<>();
        runningFiles.add("src/main/resources/Hero/Running/running0.png");
        runningFiles.add("src/main/resources/Hero/Running/running1.png");
        runningFiles.add("src/main/resources/Hero/Running/running2.png");
        runningFiles_v1.add("src/main/resources/Hero/Running/running0_v1.png");
        runningFiles_v1.add("src/main/resources/Hero/Running/running1_v1.png");
        runningFiles_v1.add("src/main/resources/Hero/Running/running2_v1.png");
        filePaths.put(GroundedState.class, runningFiles);
        filePaths_v1.put(GroundedState.class, runningFiles_v1);

        //Jumping
        List<String> jumpingFiles = new ArrayList<>();
        List<String> jumpingFiles_v1 = new ArrayList<>();
        jumpingFiles.add("src/main/resources/Hero/Jumping/jumping.png");
        jumpingFiles_v1.add("src/main/resources/Hero/Jumping/jumping_v1.png");
        filePaths.put(JumpingState.class, jumpingFiles);
        filePaths_v1.put(JumpingState.class, jumpingFiles_v1);

        //Sliding
        List<String> slidingFiles = new ArrayList<>();
        List<String> slidingFiles_v1 = new ArrayList<>();
        slidingFiles.add("src/main/resources/Hero/Sliding/sliding.png");
        slidingFiles_v1.add("src/main/resources/Hero/Sliding/sliding_v1.png");
        filePaths.put(SlidingState.class, slidingFiles);
        filePaths_v1.put(SlidingState.class, slidingFiles_v1);

        //Idle
        List<String> idleFiles = new ArrayList<>();
        List<String> idleFiles_v1 = new ArrayList<>();
        idleFiles.add("src/main/resources/Hero/Idle/idle.png");
        idleFiles_v1.add("src/main/resources/Hero/Idle/idle_v1.png");
        filePaths.put(IdleState.class, idleFiles);
        filePaths_v1.put(IdleState.class, idleFiles_v1);

        //Falling
        List<String> fallingFiles = new ArrayList<>();
        List<String> fallingFiles_v1 = new ArrayList<>();
        fallingFiles.add("src/main/resources/Hero/Falling/falling.png");
        fallingFiles_v1.add("src/main/resources/Hero/Falling/falling_v1.png");
        filePaths.put(FallingState.class, fallingFiles);
        filePaths_v1.put(FallingState.class, fallingFiles_v1);

        //WallJump
        List<String> wallJumpingFiles = new ArrayList<>();
        List<String> wallJumpingFiles_v1 = new ArrayList<>();
        wallJumpingFiles.add("src/main/resources/Hero/Idle/idle.png");
        wallJumpingFiles_v1.add("src/main/resources/Hero/WallJumping/wallJumping_v1.png");
        filePaths.put(WallJumpState.class, wallJumpingFiles);
        filePaths_v1.put(WallJumpState.class, wallJumpingFiles_v1);

        heroView = new HeroView(heroModel, filePaths, filePaths_v1);
        int width = heroView.getAnimatedSprite().getWidth();
        int height = heroView.getAnimatedSprite().getHeight();

        heroController = new HeroController(heroModel, new Vector2d(0, 0), width, height, arena);
    }

    public void update(TextGraphics graphics, double delta) {
        heroController.update(delta);
        heroView.update(graphics, delta);
    }
}

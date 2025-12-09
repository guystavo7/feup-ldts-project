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

        //Running
        List<String> runningFiles = new ArrayList<>();
        runningFiles.add("src/main/resources/Hero/Running/running0.png");
        runningFiles.add("src/main/resources/Hero/Running/running1.png");
        runningFiles.add("src/main/resources/Hero/Running/running2.png");
        filePaths.put(GroundedState.class, runningFiles);

        //Jumping
        List<String> jumpingFiles = new ArrayList<>();
        jumpingFiles.add("src/main/resources/Hero/Jumping/jumping.png");
        filePaths.put(JumpingState.class, jumpingFiles);

        //Sliding
        List<String> slidingFiles = new ArrayList<>();
        slidingFiles.add("src/main/resources/Hero/Sliding/sliding.png");
        filePaths.put(SlidingState.class, slidingFiles);

        //Idle
        List<String> idleFiles = new ArrayList<>();
        idleFiles.add("src/main/resources/Hero/Idle/idle.png");
        filePaths.put(IdleState.class, idleFiles);

        //Falling
        List<String> fallingFiles = new ArrayList<>();
        fallingFiles.add("src/main/resources/Hero/Falling/falling.png");
        filePaths.put(FallingState.class, fallingFiles);

        //WallJump
        List<String> wallJumpingFiles = new ArrayList<>();
        wallJumpingFiles.add("src/main/resources/Hero/Idle/idle.png");
        filePaths.put(WallJumpState.class, wallJumpingFiles);

        heroView = new HeroView(heroModel, filePaths);
        int width = heroView.getAnimatedSprite().getWidth();
        int height = heroView.getAnimatedSprite().getHeight();

        heroController = new HeroController(heroModel, new Vector2d(0, 0), width, height, arena);
    }

    public void update(TextGraphics graphics, double delta) {
        heroController.update(delta);
        heroView.update(graphics, delta);
        System.out.println(heroModel.isFacingRight());
    }
}

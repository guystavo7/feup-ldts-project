package com.aor.hero.mvc;

import com.aor.game.Arena;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private HeroView heroView;
    private HeroController heroController;
    private HeroModel heroModel;

    public Hero(Vector2d position, Arena arena) {
        heroModel = new HeroModel(position, new Vector2d(0, 0), new Vector2d(0, 0));
        List<String> filesPaths = new ArrayList<>();
        filesPaths.add("src/main/resources/Hero/Running/running0.png");
        filesPaths.add("src/main/resources/Hero/Running/running1.png");
        filesPaths.add("src/main/resources/Hero/Running/running2.png");

        heroView = new HeroView(heroModel, filesPaths);
        int width = heroView.getAnimatedSprite().getWidth();
        int height = heroView.getAnimatedSprite().getHeight();

        heroController = new HeroController(heroModel, new Vector2d(0, 0), width, height, arena);
    }

    public void update(TextGraphics graphics, double delta) {
        heroController.update(delta);
        heroView.update(graphics, delta);
    }
}

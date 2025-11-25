package com.aor.game;

import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero {
    private HeroView heroView;
    private HeroController heroController;
    private HeroModel heroModel;

    public Hero(Vector2d position, String filePath, Arena arena) {
        heroModel = new HeroModel(position, new Vector2d(0, 0), new Vector2d(0, 0));
        heroView = new HeroView(heroModel, filePath);
        int width = heroView.getSprite().getWidth();
        int height = heroView.getSprite().getHeight();

        heroController = new HeroController(heroModel, new Vector2d(0, 0), width, height, arena);
    }

    public void update(TextGraphics graphics, float delta) {
        heroController.update(delta);
        heroView.update(graphics);
    }
}

package com.aor.game;

import com.aor.utils.AnimatedSprite;
import com.aor.utils.GenericModel;
import com.aor.utils.GenericView;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class HeroView implements GenericView {
    private HeroModel model;
    private AnimatedSprite animatedSprite;

    public HeroView(GenericModel model, List<String> filePaths) {
        try {
            this.model = (HeroModel) model;
            animatedSprite = new AnimatedSprite(model.getPosition(), filePaths, 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }

    public void update(TextGraphics graphics, float delta) {
        animatedSprite.update(model.getPosition(), delta);
        animatedSprite.draw(graphics);
    }
}

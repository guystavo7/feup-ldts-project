package com.aor.hero;

import com.aor.components.AnimatedSprite;
import com.aor.mvc.GenericModel;
import com.aor.mvc.GenericView;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class HeroView implements GenericView {
    private HeroModel model;
    private AnimatedSprite animatedSprite;

    public HeroView(GenericModel model, List<String> filePaths) {
        try {
            this.model = (HeroModel) model;
            animatedSprite = new AnimatedSprite(this.model.getPosition(), filePaths, 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }

    @Override
    public void update(TextGraphics graphics, float delta) {
        animatedSprite.update(model.getPosition(), delta);
        animatedSprite.draw(graphics);
    }
}
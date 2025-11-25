package com.aor.game;

import com.aor.utils.GenericModel;
import com.aor.utils.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HeroView extends GenericView {
    public HeroView(GenericModel model, String filePath) {
        super(model, model.getPosition(), filePath);
    }

    public void update(TextGraphics graphics) {
        getSprite().update(getModel().getPosition());
        getSprite().draw(graphics);
    }
}

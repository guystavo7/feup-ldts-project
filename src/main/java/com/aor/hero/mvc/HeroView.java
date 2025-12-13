package com.aor.hero.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aor.components.sprites.AnimatedSprite;
import com.aor.components.factory.TransparentSpriteFactory;
import com.aor.hero.states.DashingState;
import com.aor.hero.states.IdleState;
import com.aor.mvc.GenericModel;
import com.aor.mvc.GenericView;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HeroView implements GenericView {
    private HeroModel model;
    private Map<Class, AnimatedSprite> sprites = new HashMap<>();
    private Map<Class, AnimatedSprite> sprites_v1 = new HashMap<>();
    private AnimatedSprite currentSprite;
    private Class currentState;

    public HeroView(GenericModel model, Map<Class, List<String>> filePaths, Map<Class, List<String>> filesPaths_v1) {
        try {
            this.model = (HeroModel) model;
            TransparentSpriteFactory factory = new TransparentSpriteFactory();

            for (Class state : filePaths.keySet()) {
                sprites.put(state, new AnimatedSprite(this.model.getPosition(), filePaths.get(state), 10, factory));
                sprites_v1.put(state, new AnimatedSprite(this.model.getPosition(), filesPaths_v1.get(state), 10, factory));
            }

            currentState = IdleState.class;
            currentSprite = sprites.get(currentState);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnimatedSprite getAnimatedSprite() {
        return currentSprite;
    }

    private void checkState() {
        Map<Class, AnimatedSprite> map;
        if (!model.canDash()) {
            map = sprites_v1;
        } else {
            map = sprites;
        }

        if (currentState != model.getState().getClass()) {
            currentState = model.getState().getClass();
            if (model.getState() instanceof DashingState) currentState = ((DashingState) model.getState()).getLastState();
            currentSprite = map.get(currentState);
        }
    }

    @Override
    public void update(TextGraphics graphics, double delta) {
        checkState();
        currentSprite.update(model.getPosition(), delta);

        if(model.isFacingRight()){
            currentSprite.normal();
//            for (AttachedSprite h:hair){
//                h.normal();
//            }
        }

        else {
            currentSprite.xFlip();
//            for (AttachedSprite h:hair){
//                h.xFlip();
//            }
        }
//        for (AttachedSprite h:hair){
//            h.draw(graphics);
//        }
        currentSprite.draw(graphics);
    }
}
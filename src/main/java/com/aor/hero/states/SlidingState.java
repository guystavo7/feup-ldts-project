package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroModel;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class SlidingState implements HeroState {
    private HeroModel model;

    public SlidingState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {}

    @Override
    public void onExit(double delta) {}

    @Override
    public void update(double delta) {
        getVelocity(delta);
    }

    @Override
    public void getVelocity(double delta) {
        Vector2d current = model.getVelocity();
        Vector2d target = new Vector2d(0, GameData.getInstance().getMaxVerticalVelocity() * 0.3);
        double xVelocity = model.getVelocity().getX();
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.getInstance().getGravityAcceleration() * delta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

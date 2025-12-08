package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroModel;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class FallingState implements HeroState {
    private HeroModel model;

    public FallingState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
    }

    @Override
    public void onEnter(double delta) {

    }

    @Override
    public void onExit(double delta) {}

    @Override
    public void update(double delta) {
        getVelocity(delta);
    }

    @Override
    public void getVelocity(double delta) {
        Vector2d direction = model.getDirection();
        Vector2d current = model.getVelocity();
        double maxHorizontalVelocity;
        double maxHorizontalDelta;

        if (direction.getX() == 0) {
            maxHorizontalVelocity = 0;
            maxHorizontalDelta = GameData.getInstance().getAirFriction() * delta;
        } else {
            maxHorizontalVelocity = GameData.getInstance().getMaxHorizontalVelocity() * direction.getX();
            maxHorizontalDelta = GameData.getInstance().getHorizontalAcceleration() * delta;
        }

        Vector2d target = new Vector2d(maxHorizontalVelocity, GameData.getInstance().getMaxVerticalVelocity());
        double xVelocity = Utils.moveTowards(current.getX(), target.getX(), maxHorizontalDelta);
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.getInstance().getGravityAcceleration() * delta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

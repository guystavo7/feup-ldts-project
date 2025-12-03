package com.aor.hero;

import com.aor.game.Game;
import com.aor.game.GameDataFeature;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;


public class JumpingState implements HeroState {
    private HeroModel model;

    public JumpingState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {
        model.getVelocity().setY(GameDataFeature.getInstance().getJumpForce());
    }

    @Override
    public void onExit(double delta) {}

    @Override
    public void update(double delta) {
        getVelocity(delta);
    }

    private void getVelocity(double delta) {
        Vector2d direction = model.getDirection();
        Vector2d current = model.getVelocity();
        double maxHorizontalVelocity;
        double maxHorizontalDelta;

        if (direction.getX() == 0) {
            maxHorizontalVelocity = 0;
            maxHorizontalDelta = GameDataFeature.getInstance().getAirFriction() * delta;
        } else {
            maxHorizontalVelocity = GameDataFeature.getInstance().getMaxHorizontalVelocity() * direction.getX();
            maxHorizontalDelta = GameDataFeature.getInstance().getHorizontalAcceleration() * delta;
        }

        Vector2d target = new Vector2d(maxHorizontalVelocity, GameDataFeature.getInstance().getMaxVerticalVelocity());
        double xVelocity = Utils.moveTowards(current.getX(), target.getX(), maxHorizontalDelta);
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameDataFeature.getInstance().getGravityAcceleration() * delta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}
package com.aor.hero;

import com.aor.game.Game;
import com.aor.game.GameDataFeature;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class DashingState implements HeroState {
    private HeroModel model;
    private Vector2d direction;
    private double dashVelocity = 2d;

    public DashingState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        this.direction = ((HeroModel) model).getDirection();
        if (direction.getX() == 0 && direction.getY() == 0) direction.setX(1);

        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {
        model.setVelocity(Utils.scalarProduct(direction, dashVelocity));
    }

    @Override
    public void onExit(double delta) {}

    @Override
    public void update(double delta) {
        getVelocity(delta);
    }

    private void getVelocity(double delta) {
        Vector2d current = model.getVelocity();
        double maxHorizontalDelta = GameDataFeature.getInstance().getDashFriction() * delta;

        double xVelocity = Utils.moveTowards(current.getX(), 0, maxHorizontalDelta);
        double yVelocity = Utils.moveTowards(current.getY(), 0, maxHorizontalDelta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

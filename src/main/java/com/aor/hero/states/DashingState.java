package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroController;
import com.aor.hero.mvc.HeroModel;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class DashingState implements HeroState {
    private HeroModel model;
    private Vector2d direction;
    private double dashVelocity = 3.5d;
    private Class lastState;

    public DashingState(GenericModel model, Class lastState, double delta) {
        this.model = (HeroModel) model;
        this.direction = ((HeroModel) model).getDirection();
        this.lastState = lastState;

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
    public void transition(HeroController controller, double delta) {
        if (!controller.getDashTimer().isRunning()) {
            model.setState(new FallingState(model, delta));
        }
    }

    @Override
    public void update(double delta) {
        getVelocity(delta);
    }

    @Override
    public void getVelocity(double delta) {
        Vector2d current = model.getVelocity();
        double maxHorizontalDelta = GameData.getInstance().getDashFriction() * delta;

        double xVelocity = Utils.moveTowards(current.getX(), 0, maxHorizontalDelta);
        double yVelocity = Utils.moveTowards(current.getY(), 0, maxHorizontalDelta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }

    public Class getLastState() {
        return lastState;
    }
}
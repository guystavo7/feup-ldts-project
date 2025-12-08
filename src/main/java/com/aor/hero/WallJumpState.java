package com.aor.hero;

import com.aor.game.GameData;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class WallJumpState implements HeroState {
    private HeroModel model;

    public WallJumpState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {
        model.getVelocity().setY(GameData.getInstance().getJumpForce() * 0.5);
        model.getVelocity().setX(model.getVelocity().getX() * -1);
    }

    @Override
    public void onExit(double delta) {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void getVelocity(double delta) {
        Vector2d direction = model.getDirection();
        Vector2d current = model.getVelocity();

        Vector2d target = new Vector2d(0, GameData.getInstance().getMaxVerticalVelocity());
        double xVelocity = 3d * direction.getX() * -1;
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.getInstance().getGravityAcceleration());

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

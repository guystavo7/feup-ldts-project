package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroController;
import com.aor.hero.mvc.HeroModel;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;


public class GroundedState implements HeroState {
    private HeroModel model;

    public GroundedState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {}

    @Override
    public void onExit(double delta) {}

    @Override
    public void transition(HeroController controller, double delta) {
        controller.getCoyoteTimer().start();

        if (controller.getJumpBufferTimer().isRunning()) {
            model.setState(new JumpingState(model, delta, true));

            controller.getCoyoteTimer().interrupt();
            controller.getJumpBufferTimer().interrupt();
            controller.getWallJumpTimer().interrupt();

        } else if (!controller.checkIfGrounded()) {
            model.setState(new FallingState(model, delta));

        }else if (model.getDirection().getX() == 0 && model.getDirection().getY() == 0) {
            model.setState(new IdleState(model, delta));
        }
    }

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
            maxHorizontalDelta = GameData.getInstance().getGroundFriction() * delta;
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

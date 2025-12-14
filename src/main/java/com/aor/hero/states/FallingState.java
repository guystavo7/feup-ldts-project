package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroController;
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
    public void transition(HeroController controller, double delta) {
        if (controller.getCoyoteTimer().isRunning() && controller.getJumpBufferTimer().isRunning()) {
            model.setState(new JumpingState(model, delta));

            controller.getJumpBufferTimer().interrupt();
            controller.getCoyoteTimer().interrupt();
            controller.getWallJumpTimer().interrupt();

        } else if (controller.checkIfLeftSliding() || controller.checkIfRightSliding()) {
            model.setState(new SlidingState(model, delta));

        } else if (controller.checkIfGrounded()) {
            model.setState(new GroundedState(model, delta));
            model.setCanDash(true);
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
            maxHorizontalDelta = GameData.AIR_FRICTION * delta;
        } else {
            maxHorizontalVelocity = GameData.MAX_HORIZONTAL_VELOCITY * direction.getX();
            maxHorizontalDelta = GameData.HORIZONTAL_ACCELERATION * delta;
        }

        Vector2d target = new Vector2d(maxHorizontalVelocity, GameData.MAX_VERTICAL_VELOCITY_FALLING);
        double xVelocity = Utils.moveTowards(current.getX(), target.getX(), maxHorizontalDelta);
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.GRAVITY_ACCELERATION_FALLING * delta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

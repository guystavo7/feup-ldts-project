package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroController;
import com.aor.hero.mvc.HeroModel;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class IdleState implements HeroState {
    private HeroModel model;

    public IdleState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {}

    @Override
    public void onExit(double delta) {}

    @Override
    public void transition(HeroController controller, double delta) {
        if (model.getDirection().getX() != 0) {
            model.setState(new GroundedState(model, delta));

        } else if (controller.getJumpBufferTimer().isRunning()) {
            model.setState(new JumpingState(model, delta));

            controller.getCoyoteTimer().interrupt();
            controller.getJumpBufferTimer().interrupt();
            controller.getWallJumpTimer().interrupt();
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
            maxHorizontalDelta = GameData.getInstance().groundFriction * delta;
        } else {
            maxHorizontalVelocity = GameData.getInstance().maxHorizontalVelocity * direction.getX();
            maxHorizontalDelta = GameData.getInstance().horizontalAcceleration * delta;
        }

        Vector2d target = new Vector2d(maxHorizontalVelocity, GameData.getInstance().maxVerticalVelocity);
        double xVelocity = Utils.moveTowards(current.getX(), target.getX(), maxHorizontalDelta);
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.getInstance().gravityAcceleration * delta);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

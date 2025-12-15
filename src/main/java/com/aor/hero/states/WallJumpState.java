package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroController;
import com.aor.hero.mvc.HeroModel;
import com.aor.mvc.GenericController;
import com.aor.mvc.GenericModel;
import com.aor.utils.Utils;
import com.aor.utils.Vector2d;

public class WallJumpState implements HeroState {
    private HeroModel model;

    public WallJumpState(GenericModel model, GenericController controller, double delta) {
        this.model = (HeroModel) model;
        ((HeroController) controller).playJumpSFX();
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {
        int xDirection = 1;
        if (model.isFacingRight()) xDirection = -1;

        model.getVelocity().setX(GameData.WALL_JUMP_FORCE_HORIZONTAL * xDirection * -1);
        model.getVelocity().setY(GameData.WALL_JUMP_FORCE_VERTICAL);
    }

    @Override
    public void onExit(double delta) {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void transition(HeroController controller, double delta) {
        if (!controller.getWallJumpTimer().isRunning()) {
            model.setState(new FallingState(model, delta));
        }
    }

    @Override
    public void getVelocity(double delta) {
        Vector2d current = model.getVelocity();

        Vector2d target = new Vector2d(0, GameData.MAX_VERTICAL_VELOCITY);
        double xVelocity = current.getX();
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.GRAVITY_ACCELERATION);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

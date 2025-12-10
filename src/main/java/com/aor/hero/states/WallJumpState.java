package com.aor.hero.states;

import com.aor.game.GameData;
import com.aor.hero.mvc.HeroController;
import com.aor.hero.mvc.HeroModel;
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
        model.getVelocity().setX(GameData.getInstance().maxHorizontalVelocity * 1.5 * model.getDirection().getX() * -1);
        model.getVelocity().setY(GameData.getInstance().jumpForce * 0.4);
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

        Vector2d target = new Vector2d(0, GameData.getInstance().maxVerticalVelocity);
        double xVelocity = current.getX();
        double yVelocity = Utils.moveTowards(current.getY(), target.getY(), GameData.getInstance().gravityAcceleration);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }
}

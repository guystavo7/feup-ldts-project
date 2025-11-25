package com.aor.game;

import com.aor.utils.GenericController;
import com.aor.utils.GenericModel;
import com.aor.utils.InputHandler;
import com.aor.utils.Vector2d;


import java.awt.event.KeyEvent;

public class HeroController extends GenericController {
    private boolean canJump = true;

    public HeroController(GenericModel model, Vector2d initialVelocity, int width, int height, Arena arena) {
        super(model, initialVelocity, width, height, arena);
    }

    private void getInputs(float delta) {
        if (InputHandler.getInstance().isPressed(KeyEvent.VK_LEFT)) {
            getModel().setDirectionX(-1);
        } else if (InputHandler.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
            getModel().setDirectionX(1);
        } else {
            getModel().setDirectionX(0);
        }

        if (InputHandler.getInstance().isPressed(KeyEvent.VK_UP) && canJump) {
            jump(delta);
        }
    }

    private void jump(float delta) {
        getModel().getVelocity().setY(-100 * delta);
    }

    private void getVelocity(float delta) {
        Vector2d direction = getModel().getDirection();
        float xVelocity = 60 * delta * direction.getX();
        float yVelocity = Math.min(getModel().getVelocity().getY() + 10f * delta, 100 * delta);

        getModel().setVelocity(new Vector2d(xVelocity, yVelocity));
    }

    public void update(float delta) {
        getInputs(delta);
        getVelocity(delta);

        getRigidBody().getBoxCollider().update(getModel().getPosition());
        getRigidBody().setVelocity(getModel().getVelocity());
        getRigidBody().update(getModel().getPosition());
        getModel().setPosition(getRigidBody().move());
    }
}

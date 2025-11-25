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

    private void getInputs() {
        if (InputHandler.getInstance().isPressed(KeyEvent.VK_LEFT)) {
            getModel().setDirectionX(-1);
        } else if (InputHandler.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
            getModel().setDirectionX(1);
        } else {
            getModel().setDirectionX(0);
        }

        if (InputHandler.getInstance().isPressed(KeyEvent.VK_UP) && canJump) {
            jump();
        }
    }

    private void jump() {
        getModel().getVelocity().setY(-30);
    }

    private void getVelocity(long delta) {
        Vector2d direction = getModel().getDirection();
        int xVelocity = 5 * direction.getX();
        int yVelocity = Math.min(getModel().getVelocity().getY() + 5, 10);

        getModel().setVelocity(new Vector2d(xVelocity, yVelocity));
    }

    public void update(long delta) {
        getInputs();
        getVelocity(delta);

        getRigidBody().getBoxCollider().update(getModel().getPosition());
        getRigidBody().setVelocity(getModel().getVelocity());
        getRigidBody().update(getModel().getPosition());
        getModel().setPosition(getRigidBody().move());
    }
}

package com.aor.hero;

import com.aor.components.BoxCollider;
import com.aor.components.RigidBody;
import com.aor.game.Arena;
import com.aor.game.InputHandler;
import com.aor.mvc.GenericModel;
import com.aor.utils.*;
import com.aor.mvc.GenericController;


import java.awt.event.KeyEvent;

public class HeroController implements GenericController {
    private final HeroModel model;
    private final RigidBody rigidBody;
    private final BoxCollider boxCollider;
    private boolean canJump = true;

    public HeroController(GenericModel model, Vector2d initialVelocity, int width, int height, Arena arena) {
        this.model = (HeroModel) model;
        boxCollider = new BoxCollider(this.model.getPosition(), width, height);
        rigidBody = new RigidBody(this.model.getPosition(), initialVelocity, arena, boxCollider);
    }

    private void getInputs(float delta) {
        if (InputHandler.getInstance().isPressed(KeyEvent.VK_LEFT)) {
            model.setDirectionX(-1);
        } else if (InputHandler.getInstance().isPressed(KeyEvent.VK_RIGHT)) {
            model.setDirectionX(1);
        } else {
            model.setDirectionX(0);
        }

        if (InputHandler.getInstance().isPressed(KeyEvent.VK_UP) && canJump) {
            jump(delta);
        }
    }

    private void jump(float delta) {
        model.getVelocity().setY(-100 * delta);
    }

    private void getVelocity(float delta) {
        Vector2d direction = model.getDirection();
        float xVelocity = 60 * delta * direction.getX();
        float yVelocity = Math.min(model.getVelocity().getY() + 10f * delta, 10);

        model.setVelocity(new Vector2d(xVelocity, yVelocity));
    }

    @Override
    public void update(float delta) {
        getInputs(delta);
        getVelocity(delta);

        rigidBody.getBoxCollider().update(model.getPosition());
        rigidBody.setVelocity(model.getVelocity());
        rigidBody.update(model.getPosition());
        model.setPosition(rigidBody.move());
    }

    public RigidBody getRigidBody() {
        return rigidBody;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

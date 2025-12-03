package com.aor.hero;

import com.aor.components.BoxCollider;
import com.aor.components.RigidBody;
import com.aor.game.Arena;
import com.aor.game.InputHandler;
import com.aor.mvc.GenericModel;
import com.aor.utils.*;
import com.aor.mvc.GenericController;
import com.aor.utils.Timer;


import java.awt.event.KeyEvent;

public class HeroController implements GenericController {
    private final HeroModel model;
    private final RigidBody rigidBody;
    private final BoxCollider boxCollider;
    private final BoxCollider groundCollider;
    private final Arena arena;

    private final Vector2d groundColliderOffset;

    private final Timer jumpBufferTimer;
    private final Timer coyoteTimer;
    private final Timer dashTimer;

    private boolean isGrounded = false;
    private boolean canDash = true;

    public HeroController(GenericModel model, Vector2d initialVelocity, int width, int height, Arena arena) {
        this.model = (HeroModel) model;
        this.boxCollider = new BoxCollider(this.model.getPosition(), width, height);
        this.rigidBody = new RigidBody(this.model.getPosition(), initialVelocity, arena, boxCollider);

        this.groundColliderOffset =  new Vector2d(width / 2f - 1, height - 1f);
        Vector2d groundColliderPosition = Utils.vectorSum(this.model.getPosition(), groundColliderOffset);
        this.groundCollider = new BoxCollider(groundColliderPosition, 3, 2);

        this.arena = arena;
        this.jumpBufferTimer = new Timer(0.1d, false);
        this.coyoteTimer = new Timer(0.1d, false);
        this.dashTimer = new Timer(0.2d, false);
    }

    private void checkIfGrounded() {
        if (!arena.detectCollision(groundCollider.getPosition(), groundCollider).isEmpty()) {
            coyoteTimer.start();
            isGrounded = true;
            canDash = true;
        }
    }

    private void dash(double delta) {
        model.getState().onExit(delta);
        model.setState(new DashingState(model, delta));

        canDash = false;
        isGrounded = false;

        dashTimer.start();
        coyoteTimer.interrupt();
        jumpBufferTimer.interrupt();
    }

    private void checkTransitions(double delta) {
        if (model.getState() instanceof DashingState && (!dashTimer.isRunning() || isGrounded)) {
            model.getState().onExit(delta);
            model.setState(new GroundedState(model, delta));

        } else if ((coyoteTimer.isRunning() || model.getState() instanceof GroundedState) && jumpBufferTimer.isRunning()) {
            //Jump
            model.getState().onExit(delta);
            model.setState(new JumpingState(model, delta));

            isGrounded = false;

            coyoteTimer.interrupt();
            jumpBufferTimer.interrupt();
        }
    }

    private void handleInputs(double delta) {
        //Check horizontal inputs
        if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_LEFT)) {
            model.setDirectionX(-1);
        } else if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_RIGHT)) {
            model.setDirectionX(1);
        } else {
            model.setDirectionX(0);
        }

        //Check vertical inputs
        if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_UP)) {
            model.setDirectionY(-1);
        } else if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_DOWN)) {
            model.setDirectionY(1);
        } else {
            model.setDirectionY(0);
        }

        if (InputHandler.getInstance().getKeyDown(KeyEvent.VK_C)) {
            jumpBufferTimer.start();
        }

        if (canDash && InputHandler.getInstance().getKeyDown(KeyEvent.VK_X)) {
            dash(delta);
        }
    }

    @Override
    public void update(double delta) {
        //Get inputs and calculate velocity
        handleInputs(delta);
        model.getState().update(delta);

        //Move
        rigidBody.setVelocity(model.getVelocity());

        //Update rigidBody and boxColliders and model
        model.setPosition(rigidBody.move());
        rigidBody.update(model.getPosition());
        groundCollider.update(Utils.vectorSum(model.getPosition(), groundColliderOffset));

        //Check for transitions
        checkTransitions(delta);
        checkIfGrounded();

        //Timers updates
        jumpBufferTimer.update(delta);
        coyoteTimer.update(delta);
        dashTimer.update(delta);
    }

    public RigidBody getRigidBody() {
        return rigidBody;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public BoxCollider getGroundCollider() {
        return groundCollider;
    }
}

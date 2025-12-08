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

    //Colliders
    private final BoxCollider boxCollider;
    private final BoxCollider groundCollider;
    private BoxCollider leftWallCollider;
    private BoxCollider rightWallCollider;

    private final Arena arena;

    //Timers
    private final Timer jumpBufferTimer;
    private final Timer coyoteTimer;
    private final Timer dashTimer;

    //Flags
    private boolean isGrounded = false;
    private boolean canDash = true;
    private boolean isFacingRight = true;

    public HeroController(GenericModel model, Vector2d initialVelocity, int width, int height, Arena arena) {
        this.model = (HeroModel) model;
        this.boxCollider = new BoxCollider(this.model.getPosition(), Vector2d.VECTORZERO, width, height, "player");
        this.rigidBody = new RigidBody(this.model.getPosition(), Vector2d.VECTORZERO, initialVelocity, arena, boxCollider);

        //Ground collider
        Vector2d groundColliderOffset =  new Vector2d(1, height - 1d);
        this.groundCollider = new BoxCollider(this.model.getPosition(), groundColliderOffset, 6, 2, "player");

        //Left wall collider
        Vector2d leftWallOffset = new Vector2d(-1, 0);
        this.leftWallCollider = new BoxCollider(this.model.getPosition(), leftWallOffset, 2, 7, "player");

        //Right Wall collider
        Vector2d rightWallOffset = new Vector2d(width - 1d, 0);
        this.rightWallCollider = new BoxCollider(this.model.getPosition(), rightWallOffset, 2, 7, "player");

        this.arena = arena;

        //Timers
        this.jumpBufferTimer = new Timer(0.1d, false);
        this.coyoteTimer = new Timer(0.1d, false);
        this.dashTimer = new Timer(0.2d, false);
    }

    private void checkIfGrounded() {
        if (model.getState() instanceof JumpingState) return;

        groundCollider.update(model.getPosition());
        if (!arena.detectCollision(groundCollider.getPosition(), groundCollider, "tile").isEmpty()) {
            coyoteTimer.start();
            isGrounded = true;
            canDash = true;
        }
    }

    private boolean checkIfLeftSliding() {
        leftWallCollider.update(model.getPosition());
        return !arena.detectCollision(leftWallCollider.getPosition(), leftWallCollider, "tile").isEmpty() && model.getDirection().getX() == -1;
    }

    private boolean checkIfRightSliding() {
        rightWallCollider.update(model.getPosition());
        return !arena.detectCollision(rightWallCollider.getPosition(), rightWallCollider, "tile").isEmpty() && model.getDirection().getX() == 1;
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
        if ((model.getState() instanceof SlidingState || model.getState() instanceof FallingState) && isGrounded) {
            //Was in air or sliding and hit the ground
            model.getState().onExit(delta);
            model.setState(new GroundedState(model, delta));
        }

        if (model.getState() instanceof DashingState && !dashTimer.isRunning()) {
            //If dash is over
            model.getState().onExit(delta);
            model.setState(new JumpingState(model, delta, false));

            isGrounded = false;
            coyoteTimer.interrupt();
        }

        if (model.getState() instanceof JumpingState && model.getVelocity().getY() > 0) {
            //Jumping to falling
            model.getState().onExit(delta);
            model.setState(new FallingState(model, delta));
        }

        if ((coyoteTimer.isRunning() || model.getState() instanceof GroundedState) && jumpBufferTimer.isRunning()) {
            //Jump
            model.getState().onExit(delta);
            model.setState(new JumpingState(model, delta, true));
            isGrounded = false;

            coyoteTimer.interrupt();
            jumpBufferTimer.interrupt();
        }

        if ((model.getState() instanceof JumpingState || model.getState() instanceof FallingState) && (checkIfLeftSliding() || checkIfRightSliding())) {
            //Mid air and touched walls
            model.getState().onExit(delta);
            model.setState(new SlidingState(model, delta));
        }

        if (model.getState() instanceof SlidingState && jumpBufferTimer.isRunning()) {
            model.getState().onExit(delta);
            model.setState(new WallJumpState(model, delta));

        }
    }

    private void handleInputs(double delta) {
        //Check horizontal inputs
        if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_LEFT)) {
            model.setDirectionX(-1);
            isFacingRight = false;

        } else if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_RIGHT)) {
            model.setDirectionX(1);
            isFacingRight = true;

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
        groundCollider.update(model.getPosition());

        //Check for transitions
        checkIfGrounded();
        checkTransitions(delta);

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

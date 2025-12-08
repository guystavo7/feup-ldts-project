package com.aor.hero.mvc;

import com.aor.components.BoxCollider;
import com.aor.components.RigidBody;
import com.aor.game.Arena;
import com.aor.game.GameData;
import com.aor.game.InputHandler;
import com.aor.hero.states.JumpingState;
import com.aor.hero.states.SlidingState;
import com.aor.hero.states.WallJumpState;
import com.aor.hero.states.DashingState;
import com.aor.hero.states.FallingState;
import com.aor.hero.states.GroundedState;
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
    private final BoxCollider leftWallCollider;
    private final BoxCollider rightWallCollider;
    private final Arena arena;

    //Timers
    private final Timer jumpBufferTimer;
    private final Timer coyoteTimer;
    private final Timer dashTimer;
    private final Timer wallJumpTimer;

    public HeroController(GenericModel model, Vector2d initialVelocity, int width, int height, Arena arena) {
        String playerTag = GameData.getInstance().getPlayerTag();

        this.model = (HeroModel) model;
        this.boxCollider = new BoxCollider(this.model.getPosition(), new Vector2d(0,0), width, height, playerTag);
        this.rigidBody = new RigidBody(this.model.getPosition(), new Vector2d(0, 0), initialVelocity, arena, boxCollider);

        //Ground collider
        Vector2d groundColliderOffset =  new Vector2d(2, height - 1d);
        this.groundCollider = new BoxCollider(this.model.getPosition(), groundColliderOffset, 4, 2, playerTag);

        //Left wall collider
        Vector2d leftWallOffset = new Vector2d(-1, 0);
        this.leftWallCollider = new BoxCollider(this.model.getPosition(), leftWallOffset, 2, 8, playerTag);

        //Right Wall collider
        Vector2d rightWallOffset = new Vector2d(width - 1d, 0);
        this.rightWallCollider = new BoxCollider(this.model.getPosition(), rightWallOffset, 2, 8, playerTag);

        this.arena = arena;

        //Timers
        this.jumpBufferTimer = new Timer(GameData.getInstance().getJumpBufferTime(), false);
        this.coyoteTimer = new Timer(GameData.getInstance().getCoyoteTime(), false);
        this.dashTimer = new Timer(GameData.getInstance().getDashTime(), false);
        this.wallJumpTimer = new Timer(GameData.getInstance().getWallJumpTime(), false);
    }

    private boolean checkIfGrounded() {
        groundCollider.update(model.getPosition());
        return !arena.detectCollision(groundCollider.getPosition(), groundCollider, GameData.getInstance().getTileTag()).isEmpty();
    }

    private boolean checkIfLeftSliding() {
        leftWallCollider.update(model.getPosition());
        return !arena.detectCollision(leftWallCollider.getPosition(), leftWallCollider, GameData.getInstance().getTileTag()).isEmpty() && model.getDirection().getX() < 0;
    }

    private boolean checkIfRightSliding() {
        rightWallCollider.update(model.getPosition());
        return !arena.detectCollision(rightWallCollider.getPosition(), rightWallCollider, GameData.getInstance().getTileTag()).isEmpty() && model.getDirection().getX() > 0;
    }

    private void dash(double delta) {
        model.getState().onExit(delta);
        model.setState(new DashingState(model, delta));

        model.setCanDash(false);

        dashTimer.start();
        coyoteTimer.interrupt();
        jumpBufferTimer.interrupt();
        wallJumpTimer.interrupt();
    }

    private void groundStateTransition(double delta) {
        if (jumpBufferTimer.isRunning()) {
            model.setState(new JumpingState(model, delta, true));

            coyoteTimer.interrupt();
            jumpBufferTimer.interrupt();
            wallJumpTimer.interrupt();
        }
    }

    private void jumpStateTransition(double delta) {
        if (model.getVelocity().getY() > 0) {
            model.setState(new FallingState(model, delta));

        } else if (checkIfLeftSliding() || checkIfRightSliding()) {
            model.setState(new SlidingState(model, delta));
        }
    }

    private void fallingStateTransition(double delta) {
        if (checkIfLeftSliding() || checkIfRightSliding()) {
            model.setState(new SlidingState(model, delta));

        } else if (checkIfGrounded()) {
            model.setState(new GroundedState(model, delta));
            model.setCanDash(true);
            coyoteTimer.start();
        }
    }

    private void slidingStateTransition(double delta) {
        if (checkIfGrounded()) {
            model.setState(new GroundedState(model, delta));
            model.setCanDash(true);
            coyoteTimer.start();

        } else if (jumpBufferTimer.isRunning()) {
            model.setState(new WallJumpState(model, delta));
            wallJumpTimer.start();

            coyoteTimer.interrupt();
            jumpBufferTimer.interrupt();

        } else if (!(checkIfRightSliding() || checkIfLeftSliding())) {
            model.setState(new FallingState(model, delta));
        }
    }

    private void dashingStateTransition(double delta) {
        if (!dashTimer.isRunning()) {
            model.setState(new FallingState(model, delta));
        }
    }

    private void wallJumpStateTransition(double delta) {
        if (!wallJumpTimer.isRunning()) {
            model.setState(new FallingState(model, delta));
        }
    }

    private void checkTransitions(double delta) {
        if ((coyoteTimer.isRunning() || model.getState() instanceof GroundedState)) {
            groundStateTransition(delta);

        } else if (model.getState() instanceof JumpingState) {
            jumpStateTransition(delta);

        } else if (model.getState() instanceof FallingState) {
            fallingStateTransition(delta);

        } else if (model.getState() instanceof SlidingState) {
            slidingStateTransition(delta);

        } else if (model.getState() instanceof DashingState) {
            dashingStateTransition(delta);

        } else if (model.getState() instanceof WallJumpState) {
            wallJumpStateTransition(delta);
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

        if (model.canDash() && InputHandler.getInstance().getKeyDown(KeyEvent.VK_X)) {
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
        checkTransitions(delta);

        //Timers updates
        jumpBufferTimer.update(delta);
        coyoteTimer.update(delta);
        dashTimer.update(delta);
        wallJumpTimer.update(delta);
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

package com.aor.gameElements;

import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Timer;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Strawberry extends Tile {
    private boolean active = true;
    private Timer timer;
    private boolean goingUp;

    public Strawberry(Vector2d position) throws IOException {
        super(position, new Vector2d(1, -5), 6, 8 ,GameData.tileImgsPath.get("strawberry"), GameData.STRAWBERRY_TAG);
        super.getBoxCollider().setTag(GameData.STRAWBERRY_TAG);

        timer = new Timer(GameData.STRAWBERRY_TIME, true);
        goingUp = true;
    }

    public void  kill(){
        active=false;
    }

    @Override
    public void update(TextGraphics graphics, double delta){
        active = !super.getBoxCollider().getCollidedOnce();

        if (active) {
            if (!timer.isRunning()) {
                goingUp = !goingUp;
                timer.start();
            }

            int yDirection = goingUp ? 1 : -1;
            Vector2d speed = new Vector2d(0, yDirection * GameData.STRAWBERRY_SPEED);
            super.setSpeed(speed);

            super.update(graphics,delta);
            timer.update(delta);
        }
    }

    public boolean isActive() {
        return active;
    }
}

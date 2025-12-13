package com.aor.game.state;

import com.aor.components.sprites.SolidSprite;
import com.aor.game.Game;
import com.aor.game.InputHandler;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.screen.Screen;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuState implements GameState {
    private Game game;
    private SolidSprite menuSprite;

    public MenuState(Game game) throws IOException {
        this.game = game;
        menuSprite = new SolidSprite(new Vector2d(36.5, 30), "src/main/resources/misc/titleScreen.png");
    }

    @Override
    public void update(double delta) throws IOException {
        game.getScreen().clear();
        menuSprite.draw(game.getScreen().newTextGraphics());
        game.getScreen().refresh(Screen.RefreshType.DELTA);
    }

    @Override
    public void transition(double delta) {
        if (InputHandler.getInstance().getKeyPressed(KeyEvent.VK_SPACE)) {
            game.setGameState(new RunningState(game));
        }
    }
}

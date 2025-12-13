package com.aor.game.state;

import com.aor.game.Game;
import java.io.IOException;

public class EndState implements GameState {
    private Game game;

    public EndState(Game game) {
        this.game = game;
    }

    @Override
    public void update(double delta) throws IOException {
    }

    @Override
    public void transition(double delta) {
    }

}

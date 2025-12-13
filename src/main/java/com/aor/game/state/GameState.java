package com.aor.game.state;

import java.io.IOException;

public interface GameState {
    public void update(double delta) throws IOException;
    public void transition(double delta);
}

package com.aor.game;
import java.util.HashMap;

enum KeyState {
    UP, //Key not pressed
    DOWN, //Key pressed
    CONSUMED //Key pressed and consumed but not released yet
}

public class InputHandler {
    private static InputHandler instance;
    private final HashMap<Integer, KeyState> keysPressed;
    private final HashMap<Integer, KeyState> keysDown;

    private InputHandler() {
        keysPressed = new HashMap<>();
        keysDown = new HashMap<>();
    }

    public static InputHandler getInstance() {
        if (instance == null) instance = new InputHandler();
        return instance;
    }

    public void readKeys(int key, boolean isPressed) {
        if (isPressed) {
            keysPressed.put(key, KeyState.DOWN);
            if (keysDown.get(key) != KeyState.CONSUMED) keysDown.put(key, KeyState.DOWN);

        } else {
            keysPressed.put(key, KeyState.UP);
            keysDown.put(key, KeyState.UP);
        }
    }

    public boolean getKeyPressed(int key) {
        return (keysPressed.get(key) != null) && (keysPressed.get(key) == KeyState.DOWN);
    }

    public boolean getKeyDown(int key) {
        if (keysDown.get(key) != null && keysDown.get(key) == KeyState.DOWN) {
            keysDown.put(key, KeyState.CONSUMED);
            return true;
        }

        return false;
    }
}

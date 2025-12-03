package com.aor.game;
import java.util.HashMap;

public class InputHandler {
    private static InputHandler instance;
    private HashMap<Integer, Boolean> keysPressed;
    private HashMap<Integer, Boolean> keysDown;

    private InputHandler() {
        keysPressed = new HashMap<>();
        keysDown = new HashMap<>();
    }

    public static InputHandler getInstance() {
        if (instance == null) instance = new InputHandler();
        return instance;
    }

    public void readKeys(int key, boolean isPressed) {
        keysPressed.put(key, isPressed);
        keysDown.put(key, isPressed);
    }

    public boolean getKeyPressed(int key) {
        if (Boolean.TRUE.equals(keysPressed.get(key))) return keysPressed.get(key);
        return false;
    }

    public boolean getKeyDown(int key) {
        if (Boolean.TRUE.equals(keysDown.get(key))) {
            keysDown.put(key, false);
            return true;
        }

        return false;
    }
}

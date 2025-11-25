package com.aor.utils;
import java.util.HashMap;

public class InputHandler {
    private static InputHandler instance;
    private static HashMap<Integer, Boolean> keys;

    private InputHandler() {
        keys = new HashMap<>();
    }

    public static InputHandler getInstance() {
        if (instance == null) instance = new InputHandler();
        return instance;
    }

    public void readKeys(int key, boolean isPressed) {
        keys.put(key, isPressed);
    }

    public boolean isPressed(int key) {
        return keys.get(key);
    }
}

package com.aor.game;

public class Application {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));

        Game game = new Game();
        game.start();
    }
}

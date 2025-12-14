package com.aor.game;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import com.aor.GameLoader.GameLoader;
import com.aor.game.state.*;
import com.aor.utils.Timer;
import com.aor.utils.Utils;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

public class Game {
    private Terminal terminal;
    private Screen screen;

    private boolean isRunning;
    private GameState gameState;

    private Arena arena;
    private Queue<String> arenaMaps;
    private Queue<String> backgroundMaps;

    private Timer transitionTimer;

    public void setup() {
        try {
            Font font = Utils.loadSquaredFont();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            terminal = Utils.setupTerminal(font);

            ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    InputHandler.getInstance().readKeys(e.getKeyCode(), true);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    InputHandler.getInstance().readKeys(e.getKeyCode(), false);
                }
            });

            screen = new TerminalScreen(terminal);

            //Basic Setup
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            screen.refresh();

            isRunning = true;
            arenaMaps = new ArrayDeque<>();
            backgroundMaps = new ArrayDeque<>();

            for (int i = 1; i < 6; i++) {
                String mapPath = Utils.createTempFile("Maps/map" + i + ".csv");
                String backgroundPath = Utils.createTempFile("Maps/bg" + i + ".csv");
                arenaMaps.add(mapPath);
                backgroundMaps.add(backgroundPath);
            }

            arena = new GameLoader().arenaBuilder(arenaMaps.peek(), backgroundMaps.peek());
            transitionTimer = new Timer(0.5, false);
            gameState = new MenuState(this);

        } catch (Exception e) {
            e.printStackTrace();
            isRunning = false;
        }
    }

    public void resetArena() throws IOException {
        arena = new GameLoader().arenaBuilder(arenaMaps.peek(), backgroundMaps.peek());
        transitionTimer.start();
    }

    public void nextArena() throws IOException {
        arenaMaps.poll();
        backgroundMaps.poll();
        String newArena = arenaMaps.peek();

        if (newArena != null) {
            arena = new GameLoader().arenaBuilder(arenaMaps.peek(), backgroundMaps.peek());
            transitionTimer.start();

        } else {
            gameState = new EndState(this);
        }
    }

    public Game() {
        setup();
        start();
    }

    public void start() {
        double frames = 60;
        double framePerNanoSecond = 1_000_000_000d / frames;
        double previousTime = System.nanoTime();

        while (isRunning) {
            try {
                KeyStroke key = screen.pollInput();
                if (key != null && key.getKeyType().equals(KeyType.EOF)) isRunning = false;
            } catch (IOException e) {
                isRunning = false;
            }

            double currentTime = System.nanoTime();
            double delta = currentTime - previousTime;

            if (delta >= framePerNanoSecond) {
                previousTime = currentTime;

                try {
                    gameState.update(delta);
                    gameState.transition(delta);
                } catch (Exception e) {
                    isRunning = false;
                }

            } else {
                double sleepTime = ((framePerNanoSecond - delta) / 1000000);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep((long) sleepTime);

                    } catch (InterruptedException e) {
                        isRunning = false;
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        try {
            close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGameState(GameState newGameState) {
        gameState = newGameState;
    }

    public Screen getScreen() {
        return screen;
    }

    public Timer getTransitionTimer() {
        return transitionTimer;
    }

    public Arena getArena() {
        return arena;
    }

    public void close() throws IOException {
        if (screen == null) return;
        screen.close();
        terminal.close();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void resetGame() {
        setup();
    }

}

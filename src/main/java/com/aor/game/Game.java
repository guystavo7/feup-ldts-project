package com.aor.game;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.aor.GameLoader.GameLoader;
import com.aor.utils.Utils;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private Arena arena;
    private boolean isRunning;

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
            arena = new GameLoader().arenaBulder("src/main/java/com/aor/GameLoader/loadTiles1.txt");

        } catch (Exception e) {
            e.printStackTrace();
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
            double currentTime = System.nanoTime();
            double delta = currentTime - previousTime;

            if (delta >= framePerNanoSecond) {
                previousTime = currentTime;

                try {
                    screen.clear();
                    arena.update(screen.newTextGraphics(), delta / 1_000_000_000d);
                    screen.refresh(Screen.RefreshType.DELTA);
                    isRunning = !arena.isHeroDead() && !arena.isArenaCompleted();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                double sleepTime = ((framePerNanoSecond - delta) / 1000000);
                if (sleepTime > 0) {
                    try {
                        Thread.sleep((long) sleepTime);

                    } catch (InterruptedException e) {
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

    private void close() throws IOException {
        screen.close();
        terminal.close();
    }
}

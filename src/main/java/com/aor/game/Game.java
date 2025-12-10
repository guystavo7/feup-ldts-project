package com.aor.game;

import com.aor.utils.Utils;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

public class Game {
    private Screen screen;
    private Arena arena;

    private boolean isRunning;

    public void setup() {
        try {
            Font font = Utils.loadSquaredFont();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            Terminal terminal = Utils.setupTerminal(font);

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
            arena = new Arena();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game() {
        setup();
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
    }
}

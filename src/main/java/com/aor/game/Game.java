package com.aor.game;

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

    private int screenWidth = 128;
    private int screenHeight = 128;

    private boolean isRunning;
    private long previousTime;

    public Game() {
        try {
            URL resource = getClass().getClassLoader().getResource("square.ttf");
            assert resource != null;

            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            //Size setup
            TerminalSize terminalSize = new TerminalSize(screenWidth, screenHeight);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

            Font loadedFont = font.deriveFont(Font.PLAIN, 5);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setInitialTerminalSize(terminalSize);

            //Terminal Setup
            Terminal terminal = terminalFactory.createTerminal();

            ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });
            
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

    public void start() {
        Thread.ofVirtual().start(() -> {
            previousTime = 0;

            while (isRunning) {
                try {
                    long currentTime = System.currentTimeMillis();

                    if (previousTime == 0) currentTime = 0;
                    long delta = currentTime - previousTime;

                    screen.clear();
                    arena.update(screen.newTextGraphics(), (float) delta / 1000);
                    screen.refresh(Screen.RefreshType.DELTA);

                    previousTime = System.currentTimeMillis();

                    long delay_time=1000 / 60-(previousTime-currentTime);
                    delay_time=delay_time>0?delay_time:0;
                    Thread.sleep(delay_time);
                } catch (Exception e) {
                    e.printStackTrace();
                   // Thread.currentThread().interrupt();
                }
            }
        });
    }
}

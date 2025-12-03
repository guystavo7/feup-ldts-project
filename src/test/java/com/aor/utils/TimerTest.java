package com.aor.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimerTest {
    Timer timer;

    @BeforeEach
    void setup() {
        timer = new Timer(5, false);
    }

    @Test
    void testRunning() {
        //Start timer and test isRunning
        timer.start();

        //0 seconds passed
        Assertions.assertTrue(timer.isRunning());
        Assertions.assertEquals(5f, timer.getRemaingTime());

        //1 second passed
        timer.update(1);
        Assertions.assertTrue(timer.isRunning());
        Assertions.assertEquals(4f, timer.getRemaingTime());

        //5 seconds passed
        timer.update(4);
        Assertions.assertFalse(timer.isRunning());
        Assertions.assertEquals(0f, timer.getRemaingTime());

        //6 seconds passed
        timer.update(1);
        Assertions.assertFalse(timer.isRunning());
        Assertions.assertEquals(0f, timer.getRemaingTime());

        //Restart timer
        timer.start();
        Assertions.assertTrue(timer.isRunning());
        Assertions.assertEquals(5f, timer.getRemaingTime());
    }

    @Test
    void testInterrupt() {
        timer.start();

        //0 seconds passed
        Assertions.assertTrue(timer.isRunning());

        //timer is interrupted
        timer.interrupt();
        Assertions.assertFalse(timer.isRunning());
    }
}

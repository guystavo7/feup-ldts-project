package com.aor.utils;

public class Timer {
    private double remaingTime;
    private double duration;
    private boolean running = false;

    public Timer(double duration, boolean autoStart) {
        this.duration = duration;
        if (autoStart) start();
    }

    public void start() {
        running = true;
        remaingTime = duration;
    }

    public boolean isRunning() {
        return running;
    }

    public void interrupt() {
        running = false;
        remaingTime = 0f;
    }

    public void update(double delta) {
        if (running) remaingTime -= delta;
        if (remaingTime <= 0) interrupt();
    }

    public double getRemaingTime() {
        return remaingTime;
    }
}

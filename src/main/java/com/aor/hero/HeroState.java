package com.aor.hero;

public interface HeroState {
    public void onEnter(double delta);
    public void onExit(double delta);
    public void update(double delta);
    public void getVelocity(double delta);
}
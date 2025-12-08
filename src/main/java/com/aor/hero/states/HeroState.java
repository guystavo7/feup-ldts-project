package com.aor.hero.states;

public interface HeroState {
    public void onEnter(double delta);
    public void onExit(double delta);
    public void update(double delta);
    public void getVelocity(double delta);
}
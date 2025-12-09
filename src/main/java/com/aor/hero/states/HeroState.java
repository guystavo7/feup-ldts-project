package com.aor.hero.states;

import com.aor.hero.mvc.HeroController;

public interface HeroState {
    public void onEnter(double delta);
    public void onExit(double delta);
    public void update(double delta);
    public void getVelocity(double delta);
    public void transition(HeroController controller, double delta);
}
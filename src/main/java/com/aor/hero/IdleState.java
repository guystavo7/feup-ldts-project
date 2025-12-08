package com.aor.hero;

import com.aor.mvc.GenericModel;

public class IdleState implements HeroState {
    private HeroModel model;

    public IdleState(GenericModel model, double delta) {
        this.model = (HeroModel) model;
        onEnter(delta);
    }

    @Override
    public void onEnter(double delta) {}

    @Override
    public void onExit(double delta) {}

    @Override
    public void update(double delta) {
        getVelocity(delta);
    }

    @Override
    public void getVelocity(double delta) {}
}

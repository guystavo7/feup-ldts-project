package com.aor.GameLoader;

import com.aor.arena.Arena;
import com.aor.hero.mvc.Hero;
import com.aor.utils.Vector2d;

import java.io.IOException;

public class HeroLoader implements GameElementLoader{
    Arena arena;
    HeroLoader(){
        this.arena=null;
    }
    void setArena(Arena arena){
        this.arena=arena;
    }
    Arena getArena(){
        return arena;
    }

    @Override
    public Hero Parse(String data, Vector2d pos) throws IOException {
        return new Hero(pos,arena);
    }

}

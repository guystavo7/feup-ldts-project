package com.aor.game;

import java.util.ArrayList;
import java.util.List;

import com.aor.components.BoxCollider;
import com.aor.gameElements.EndBlock;
import com.aor.gameElements.GroundVoidBlock;
import com.aor.gameElements.GroundedSpike;
import com.aor.gameElements.Strawberry;
import com.aor.hero.mvc.Hero;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;

public class Arena {
    private Hero hero;
    private boolean isHeroDead = false;
    private boolean isArenaCompleted = false;

    private List<Tile> generalTiles=new ArrayList<>();

    private List<Tile> tiles;

    private List<Strawberry> strawberries;
    private List<EndBlock> endBlocks;
    private List<GroundedSpike> spikes;

    private List<GroundVoidBlock> voids;
    private Integer n_berrys=0;

    public Arena(Hero hero, List<Tile> tiles,List<Strawberry> strawberries,List<EndBlock> endBlocks,List<GroundedSpike> spikes,List<GroundVoidBlock> voids) {
        this.hero=hero;
        this.tiles=tiles;
        this.strawberries=strawberries;
        this.endBlocks=endBlocks;
        this.spikes=spikes;
        this.voids=voids;

        for(Strawberry s : strawberries) {
            generalTiles.add(s);
        }

        for(Tile t : tiles) {
            generalTiles.add(t);
        }

        for(GroundedSpike s : spikes) {
            generalTiles.add(s);
        }

        for (EndBlock e: endBlocks){
            generalTiles.add(e);
        }

        for (GroundVoidBlock v:voids ){
            generalTiles.add(v);
        }

    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    private boolean checkIfHeroIsDead() {
        for (Tile tile : spikes) {
            if (tile.getBoxCollider().getCollidedOnce()) return false;
        }

        for (Tile tile : voids) {
            if (tile.getBoxCollider().getCollidedOnce()) return false;
        }

        return true;
    }

    private boolean checkIfLevelComplete() {
        for (Tile tile : endBlocks) {
            if (tile.getBoxCollider().getCollidedOnce()) return true;
        }

        return false;
    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider, String searchTag) {
        List<BoxCollider> colliders = new ArrayList<>();
        Vector2d oldPos = new Vector2d(boxCollider.getPosition().getX(), boxCollider.getPosition().getY());
        boxCollider.setPosition(newPos);

        for (Tile tile : generalTiles) {
            BoxCollider tileBoxCollider = tile.getBoxCollider();
            if (!boxCollider.isColliding(tileBoxCollider)) continue;

            if (searchTag.equals(GameData.ALL_COLLIDERS) || searchTag.equals(tileBoxCollider.getTag())) {
                tileBoxCollider.firstCollide();
                if (tileBoxCollider.getTag().equals(GameData.TILE_TAG)) colliders.add(tileBoxCollider);
            }
        }

        boxCollider.setPosition(oldPos);
        return colliders;
    }

    public void update(TextGraphics graphics, double delta) {
        for (Tile t : generalTiles) {
            t.update(graphics, delta);
        }

        hero.update(graphics, delta);
        isHeroDead = !checkIfHeroIsDead();
        isArenaCompleted = checkIfLevelComplete();
    }

    public boolean isHeroDead() {
        return isHeroDead;
    }
    public boolean isArenaCompleted() {
        return isArenaCompleted;
    }
}

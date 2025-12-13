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
    private List<BoxCollider> walls = new ArrayList<>();
    private boolean isHeroDead = false;
    private boolean isArenaCompleted = false;

    private List<Tile> generalTiles=new ArrayList<>();

    private List<Tile> tiles;

    private List<Strawberry> strawberries;
    private List<EndBlock> endBlocks;
    private List<GroundedSpike> spikes;

    private List<GroundVoidBlock> voids;
    private Integer n_berrys=0;

    public Arena(Hero hero,List<Tile> tiles,List<Strawberry> strawberries,List<EndBlock> endBlocks,List<GroundedSpike> spikes,List<GroundVoidBlock> voids) {
        this.hero=hero;
        this.tiles=tiles;
        this.strawberries=strawberries;
        this.endBlocks=endBlocks;
        this.spikes=spikes;
        this.voids=voids;

        for(Strawberry s : strawberries) {
            walls.add(s.getBoxCollider());
            generalTiles.add(s);
        }


        for(Tile t : tiles) {
            walls.add(t.getBoxCollider());
            generalTiles.add(t);
        }

        for(GroundedSpike s : spikes) {
            walls.add(s.getBoxCollider());
            generalTiles.add(s);
        }

        for (EndBlock e: endBlocks){
            walls.add(e.getBoxCollider());
            generalTiles.add(e);
        }


        for (GroundVoidBlock v:voids ){
            walls.add(v.getBoxCollider());
            generalTiles.add(v);
        }

        BoxCollider leftInvWalL = new BoxCollider(new Vector2d(-10, 0), 10, GameData.SCREEN_HEIGHT, GameData.TILE_TAG);
        BoxCollider rightInvWall = new BoxCollider(new Vector2d(GameData.SCREEN_WIDTH, 0), 10, GameData.SCREEN_HEIGHT, GameData.TILE_TAG);
        walls.add(leftInvWalL);
        walls.add(rightInvWall);
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void update(TextGraphics graphics, double delta) {
        hero.update(graphics, delta);
        for(Tile t:generalTiles) {
            t.update(graphics, delta);
        }
    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider, String tag) {
        List<BoxCollider> colliders = new ArrayList<>();
        Vector2d oldPos = new Vector2d(boxCollider.getPosition().getX(), boxCollider.getPosition().getY());
        boxCollider.setPosition(newPos);

        for (BoxCollider box : walls) {
            if (boxCollider.isColliding(box)){
                box.firstCollide();
                
                if(boxCollider.getTag().equals(GameData.PLAYER_TAG)){ // o player procura por colisões na arena
                    if(box.getTag().equals(GameData.SPIKE_TAG)||box.getTag().equals(GameData.VOID_TILE_TAG)){
                        isHeroDead=true;
                    }
                    else if(box.getTag().equals(GameData.END_BLOCK_TAG)){
                        isArenaCompleted=true;
                    }
                }

                if(tag.equals(box.getTag())){
                    colliders.add(box);
                }
            }

        }

        boxCollider.setPosition(oldPos);
        return colliders;
    }

    public boolean isHeroDead() {
        return isHeroDead;
    }
    public boolean isArenaCompleted() {
        return isArenaCompleted;
    }
}

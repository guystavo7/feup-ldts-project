package com.aor.game;

import com.aor.GameLoader.GameLoader;
import com.aor.gameElements.EndBlock;
import com.aor.gameElements.GroundVoidBlock;
import com.aor.gameElements.GroundedSpike;
import com.aor.gameElements.Strawberry;
import com.aor.tile.Tile;
import com.aor.hero.mvc.Hero;
import com.aor.components.BoxCollider;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero hero;
    private List<BoxCollider> walls;
    private boolean isHeroDead=false;
    private boolean isArenaCompleted=false;
    List<Tile> tiles=new ArrayList<>();

    List<Strawberry> strawberries=new ArrayList<>();
    List<EndBlock> endBlocks=new ArrayList<>();
    List<GroundedSpike> spikes=new ArrayList<>();

    List<GroundVoidBlock> voids=new ArrayList<>();
    Integer n_berrys=0;

    public Arena() {
        try {
            hero = new Hero(new Vector2d(25, 0), this);
            walls = new ArrayList<>();
            GameLoader gl = new GameLoader();
            String loadFile="src/main/resources/Tiles/loadTiles.txt";
            tiles = gl.loadTile16x16file(loadFile);
            strawberries=gl.loadStrawberry16x16file(loadFile);
            endBlocks=gl.loadEndBlock16x16file(loadFile);
            spikes=gl.loadSpike16x16file(loadFile);
            voids=gl.loadVoid16x16file(loadFile);
            //  ed=new EndBlock(new Vector2d(16*8,9*8));

            for(Strawberry s : strawberries) {
                walls.add(s.getBoxCollider());
            }


            for(Tile t : tiles) {
                walls.add(t.getBoxCollider());
            }

            for(GroundedSpike s : spikes) {
                walls.add(s.getBoxCollider());
            }

            for (EndBlock e: endBlocks){
                walls.add(e.getBoxCollider());
            }


            for (GroundVoidBlock v:voids ){
                walls.add(v.getBoxCollider());
            }



            BoxCollider wall2 = new BoxCollider(new Vector2d(-10, 0), new Vector2d(0, 0), 10, 1000, "tile");
            walls.add(wall2);
            BoxCollider wall3 = new BoxCollider(new Vector2d(128, 0), new Vector2d(0, 0), 10, 1000, "tile");
            walls.add(wall3);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(TextGraphics graphics, double delta) {
        hero.update(graphics, delta);
        for(Tile t:tiles) {
            t.update(graphics, delta);
        }

        for(GroundedSpike s : spikes) {
            s.update(graphics,delta);
        }

        for(Strawberry s : strawberries) {
            s.update(graphics,delta);
        }


        for(EndBlock e : endBlocks) {
            e.update(graphics,delta);
        }


        for(GroundVoidBlock v : voids) {
            v.update(graphics,delta);
        }

    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider, String tag) {
        List<BoxCollider> colliders = new ArrayList<>();
        Vector2d oldPos = new Vector2d(boxCollider.getPosition().getX(), boxCollider.getPosition().getY());
        boxCollider.setPosition(newPos);

        for (BoxCollider box : walls) {

            if (boxCollider.isColliding(box)){
                box.firstCollide();
                if(boxCollider.getTag().equals(GameData.getInstance().getPlayerTag())){ // o player procura por colisões na arena
                    if(box.getTag().equals(GameData.spikeTag)||box.getTag().equals(GameData.voidBlockTag)){
                        isHeroDead=true;
                    }
                    else if(box.getTag().equals(GameData.EndBlockTag)){
                        isArenaCompleted=true;
                    }
                }
                if(tag.equals(box.getTag())){
                    colliders.add(box);
                }
                // if (tag.equals("colliders") && !tag.equals(box.getTag())) continue;

            };

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
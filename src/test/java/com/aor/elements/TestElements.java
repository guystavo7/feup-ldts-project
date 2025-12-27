package com.aor.elements;

import com.aor.components.BoxCollider;
import com.aor.game.Arena;
import com.aor.game.GameData;
import com.aor.game.InputHandler;
import com.aor.game_loader.ArenaLoader;
import com.aor.hero.mvc.Hero;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestElements {
    public class StubHero extends Hero{
        public StubHero(Vector2d pos,Arena arena){
            super(pos,arena);
        }

        public BoxCollider getHitBox(){
            return super.heroController.getHitBox();
        }

        public BoxCollider getBoxCollider(){
            return super.heroController.getBoxCollider();
        }

        public Vector2d getPosition(){return super.heroModel.getPosition();}
    }

    Strawberry berry;
    StubHero mockedHero;
    double delta=1d/60d;
    TextGraphics mockedGraphics=Mockito.mock(TextGraphics.class);
    ArenaLoader arenaLoader=new ArenaLoader();;
    String TilesLoaderFile="src/test/java/com/aor/elements/testing_map.txt";


    @Test
    public void BerryIsTaken() throws IOException {

        Vector2d heroPos= new Vector2d((2%14+1)*8,(13%14+1)*8);  //Hero occupies the tile coordinate (3,14)


        berry=new Strawberry(new Vector2d((4%14+1)*8,(13%14+1)*8)); //Berry occupies the tile coordinate (5,14)

        Arena arena=new Arena(null,arenaLoader.loadTile16x16file(TilesLoaderFile), List.of(berry),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        mockedHero=new StubHero(heroPos,arena);
        arena.setHero(mockedHero);

        InputHandler.getInstance().readKeys(KeyEvent.VK_RIGHT,true);// Hero is walking towards the berry

        while (!mockedHero.getHitBox().isColliding(berry.getBoxCollider())){ // When Hero touches the berry
            arena.update(mockedGraphics,delta);
        }
        arena.update(mockedGraphics,delta);
        Assertions.assertFalse(berry.isActive()); // berry is collected
    }

    @Test
    public void SpikeIsDeadly() throws IOException {

        Vector2d heroPos= new Vector2d((2%14+1)*8,(13%14+1)*8); //Hero occupies the tile coordinate (3,14)
        Vector2d spikePos=new Vector2d((4%14+1)*8,(13%14+1)*8); //Spike occupies the tile coordinate (5,14)

        GroundedSpike spike=new GroundedSpike(spikePos);

        Arena arena=new Arena(null,arenaLoader.loadTile16x16file(TilesLoaderFile), new ArrayList<>(),new ArrayList<>(),List.of(spike),new ArrayList<>(),new ArrayList<>());
        mockedHero=new StubHero(heroPos,arena);
        arena.setHero(mockedHero);

        InputHandler.getInstance().readKeys(KeyEvent.VK_RIGHT,true); // Hero is walking towards the spike

        while (!mockedHero.getHitBox().isColliding(spike.getBoxCollider())){ // When Hero touches the spike
            arena.update(mockedGraphics,delta);
        }
        arena.update(mockedGraphics,delta);

        Assertions.assertTrue(arena.isHeroDead()); //Hero dies
    }

    @Test
    public void VoidBlockIsDeadly() throws IOException {

        Vector2d heroPos= new Vector2d((2%14+1)*8,(13%14+1)*8); //Hero occupies the tile coordinate (3,14)

        GroundVoidBlock voidBlock=new GroundVoidBlock(new Vector2d((4%14+1)*8,(12%14+1)*8)); //voidBlock occupies the tile coordinate (5,13) , just a note: the y-axis points downwards

        Arena arena=new Arena(null,arenaLoader.loadTile16x16file(TilesLoaderFile), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),List.of(voidBlock),new ArrayList<>());
        mockedHero=new StubHero(heroPos,arena);
        arena.setHero(mockedHero);

        InputHandler.getInstance().readKeys(KeyEvent.VK_RIGHT,true); // Hero is walking towards the voidBlock

        while (!mockedHero.getHitBox().isColliding(voidBlock.getBoxCollider())){ // When Hero touches the voidBlock
            arena.update(mockedGraphics,delta);
        }
        arena.update(mockedGraphics,delta);

        Assertions.assertTrue(arena.isHeroDead()); //Hero dies
    }

    @Test
    public void EndBlockCompletesArena() throws IOException {

        Vector2d heroPos= new Vector2d((2%14+1)*8,(13%14+1)*8); //Hero occupies the tile coordinate (3,14)

        EndBlock endBlock =new EndBlock(new Vector2d((4%14+1)*8,(13%14+1)*8+4)); //endBlock occupies the tile coordinate (5,14)

        Arena arena=new Arena(null,arenaLoader.loadTile16x16file(TilesLoaderFile), new ArrayList<>(),List.of(endBlock),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        mockedHero=new StubHero(heroPos,arena);
        arena.setHero(mockedHero);
        InputHandler.getInstance().readKeys(KeyEvent.VK_RIGHT,true); // Hero is walking towards the endBlock

        while (!mockedHero.getHitBox().isColliding(endBlock.getBoxCollider())){ // When Hero touches the endBlock
            arena.update(mockedGraphics,delta);
        }
        arena.update(mockedGraphics,delta);

        Assertions.assertTrue(arena.isArenaCompleted()); //Arena is completed
    }

    @Test
    public void BackgroundDontCollideWithHero() throws IOException {

        Vector2d heroPos= new Vector2d((2%14+1)*8,(13%14+1)*8); //Hero occupies the tile coordinate (3,14)

        BackgroundBlock backgroundBlock =new BackgroundBlock(new Vector2d((4%14+1)*8,(13%14+1)*8), GameData.getTileImgPath().get("flower")); //backgroundBlock occupies the tile coordinate (5,14)

        Arena arena=new Arena(null,arenaLoader.loadTile16x16file(TilesLoaderFile), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),List.of(backgroundBlock));
        mockedHero=new StubHero(heroPos,arena);
        arena.setHero(mockedHero);
        InputHandler.getInstance().readKeys(KeyEvent.VK_RIGHT,true); // Hero is walking towards the backgroundBlock

        while (!mockedHero.getHitBox().isColliding(backgroundBlock.getBoxCollider())){ // When Hero touches the backgroundBlock
            arena.update(mockedGraphics,delta);
        }
        Vector2d collidingPos=mockedHero.getPosition();
        arena.update(mockedGraphics,delta);

        Assertions.assertTrue(mockedHero.getPosition().getX()>collidingPos.getX()); //Hero is not hindered
    }


    @Test
    public void NormalTileCollideWithHero() throws IOException {

        Vector2d heroPos= new Vector2d((2%14+1)*8,(13%14+1)*8); //Hero occupies the tile coordinate (3,14)
        Vector2d tilePos=new Vector2d((4%14+1)*8,(13%14+1)*8);
        Tile tile =new Tile(tilePos, GameData.getTileImgPath().get("T11"),GameData.TILE_TAG); //tile occupies the tile coordinate (5,14)
        List<Tile> tiles=arenaLoader.loadTile16x16file(TilesLoaderFile);
        tiles.add(tile);
        Arena arena=new Arena(null,tiles, new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        mockedHero=new StubHero(heroPos,arena);
        arena.setHero(mockedHero);
        InputHandler.getInstance().readKeys(KeyEvent.VK_RIGHT,true); // Hero is walking towards the tile
        int heroWidth=8;
        while (mockedHero.getPosition().getX()+heroWidth< tilePos.getX()){ // When Hero touches the tile
            arena.update(mockedGraphics,delta);
        }
        arena.update(mockedGraphics,delta);

        Vector2d collidingPos=mockedHero.getPosition();

        arena.update(mockedGraphics,delta);

        Assertions.assertFalse(mockedHero.getPosition().getX()>collidingPos.getX()); //Hero can not go further
    }


}

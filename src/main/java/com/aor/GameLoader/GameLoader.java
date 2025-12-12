package com.aor.GameLoader;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aor.game.Arena;
import com.aor.gameElements.EndBlock;
import com.aor.gameElements.GroundVoidBlock;
import com.aor.gameElements.GroundedSpike;
import com.aor.gameElements.Strawberry;
import com.aor.hero.mvc.Hero;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;

import javax.swing.*;

public class GameLoader {
    Map<Character,GameElementLoader> loader=Map.of('T', new TileLoader() ,'E',new EndBlockLoader(),'S',new GroundedSpikeLoader(),'V',new GroundedVoidBlockLoader(),'B',new StraberryLoader(),'H',new HeroLoader());
    public List<GameElement> load16x16file(String filePath) throws IOException {
        List<GameElement>res=new ArrayList<>();
        DataInputStream data=new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
        StringBuilder sbdata=new StringBuilder();
        boolean possible=true;
        while (possible){
            try {
                Byte cdata=data.readByte();
                char c=(char)cdata.byteValue();
                sbdata.append(c);
            }
            catch (Exception e){
                possible=false;
            }
        }
        String sdata=sbdata.toString();

        String[] divdata= sdata.split("[,]");
        int counter=0;

        for(String d:divdata){
            d=d.trim();
            if(!d.equals("")){
                Vector2d pos=new Vector2d((float) counter%16,(float) ((int)counter/16));
                res.add(loader.get(d.charAt(0)).Parse(d.substring(1),pos));

            }
            counter++;

        }

        return res;
    }


    public  List<GameElement> load16x16File(String filePath,Character id) throws IOException {
        List<GameElement>res=new ArrayList<>();
        DataInputStream data=new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
        StringBuilder sbdata=new StringBuilder();

        boolean possible=true;

        while (possible){
            try {
                Byte cdata=data.readByte();
                char c=(char)cdata.byteValue();
                if(c!='\n'){
                    sbdata.append(c);
                }
            }
            catch (Exception e){
                possible=false;
            }
        }
        String sdata=sbdata.toString();

        String[] divdata= sdata.split("[,]");
        int counter=0;

        for(String d:divdata){
            d=d.trim();
            if( !(d.isEmpty()) && d.charAt(0)==id){
                Vector2d pos = new Vector2d((float) (counter % 16) * 8,(float) ((int) counter / 16) * 8);
                res.add( loader.get(id).Parse(d.substring(1),pos));

            }
            counter++;

        }


        return res;
    }
    public List<Tile> loadTile16x16file(String filePath) throws IOException {
        return this.convert(load16x16File(filePath, 'T'));
    }
    public List<EndBlock> loadEndBlock16x16file(String filePath) throws IOException {
        return this.convert(load16x16File(filePath, 'E'));
    }

    public List<GroundedSpike> loadSpike16x16file(String filePath) throws IOException {
        return this.convert(load16x16File(filePath, 'S'));
    }

    public List<GroundVoidBlock> loadVoid16x16file(String filePath) throws IOException {
        return this.convert(load16x16File(filePath, 'V'));
    }


    public List<Strawberry> loadStrawberry16x16file(String filePath) throws IOException {
        return this.convert(load16x16File(filePath, 'B'));
    }

    public List<Hero> loadHero16x16file(String filePath, Arena arena) throws IOException {
        ((HeroLoader)loader.get('H')).setArena(arena);
        return this.convert(load16x16File(filePath, 'H'));
    }

    public Arena arenaBulder(String filePath) throws IOException {

        List<Tile> tiles=loadTile16x16file(filePath);
        List<Strawberry> strawberries=loadStrawberry16x16file(filePath);
        List<EndBlock> endBlocks=loadEndBlock16x16file(filePath);
        List<GroundedSpike> spikes=loadSpike16x16file(filePath);

        List<GroundVoidBlock> voids=loadVoid16x16file(filePath);
        Arena arena=new Arena(null,tiles,strawberries,endBlocks,spikes,voids);
        List<Hero>  heroes=loadHero16x16file(filePath, arena);
        arena.setHero(heroes.get(0));
        return arena;
    }




    public <T> List<T> convert(List<GameElement>elems){
        List<T> res= new ArrayList<>();
        for (GameElement e : elems) {
            res.add((T) e);
        }
        return res ;
    }
        /*

        List<Tile>res=new ArrayList<>();
        DataInputStream data=new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
        StringBuilder sbdata=new StringBuilder();

        boolean possible=true;

        while (possible){
            try {
                Byte cdata=data.readByte();
                char c=(char)cdata.byteValue();
                if(c!='\n'){
                    sbdata.append(c);
                }
            }
            catch (Exception e){
                possible=false;
            }
        }
        String sdata=sbdata.toString();

        String[] divdata= sdata.split("[,]");
        int counter=0;

        for(String d:divdata){
            d=d.trim();
            if( !(d.isEmpty()) && d.charAt(0)=='T'){
                Vector2d pos = new Vector2d((float) (counter % 16) * 8,(float) ((int) counter / 16) * 8);
                res.add((Tile) loader.get('T').Parse(d.substring(1),pos));

            }
            counter++;

        }


        return res;*/


}

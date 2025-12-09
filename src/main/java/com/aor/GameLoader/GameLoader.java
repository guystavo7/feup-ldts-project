package com.aor.GameLoader;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aor.tiles.Tile;
import com.aor.utils.Vector2d;

public class GameLoader {
    Map<Character,GameElementLoader> loader=Map.of('T', new TileLoader() );
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


    public List<Tile> loadTile16x16file(String filePath) throws IOException {
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


        return res;
    }

}

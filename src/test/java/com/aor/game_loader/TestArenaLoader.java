package com.aor.game_loader;

import com.aor.elements.GameElement;
import com.aor.game.GameData;
import com.aor.tile.Tile;
import com.aor.utils.Vector2d;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class TestArenaLoader {
    String testLoaderFilename="src/test/java/com/aor/game_loader/TestLoaderFile.txt";
    ArenaLoader arenaLoader;
    Map<String,String> TileImagePaths= GameData.getTileImgPath();
    List<String> TileCodes;
    boolean setupOnce=false;
    @BeforeEach
    void setup(){
        if(!setupOnce) {
            TileCodes = new ArrayList<>();
            arenaLoader = new ArenaLoader();
            for (String s : TileImagePaths.keySet()) {
                if (s.startsWith("T")) {
                    TileCodes.add(s);
                }
            }
            TileCodes.add("  ");
            setupOnce=true;
        }
    }



    @Property(tries=1)
    void TestTileLoader(@ForAll int seed ) throws IOException {
        setup();
        File file=new File(testLoaderFilename);
        if(!file.exists()){
            file.createNewFile();
            file.setReadable(true);
            file.setExecutable(true);
        }

        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(testLoaderFilename));
        Random random=new Random();
        random.setSeed(seed);
        List<Tile>tiles=new ArrayList<>();
        StringBuilder encodingStringBuilder=new StringBuilder();
        for(int j=0;j<16;j++){
          for(int i=0;i<16;i++){
                Integer randomId=random.nextInt()%TileCodes.size();
                randomId=randomId<0?-randomId:randomId;
                String tileCode=TileCodes.get(randomId);
                if(!tileCode.equals("  ")) {
                    tiles.add(new Tile(new Vector2d(i * 8, j * 8), TileImagePaths.get(tileCode), GameData.TILE_TAG));
                }
                encodingStringBuilder.append(tileCode+",");
            }
            encodingStringBuilder.append("\n");
        }
        String encoingString=encodingStringBuilder.toString();
        bufferedWriter.write(encoingString);
        bufferedWriter.flush();
        bufferedWriter.close();
        List<Tile> tiles1= arenaLoader.loadTile16x16file(testLoaderFilename);
         Assertions.assertEquals( tiles1,tiles);

        //file.deleteOnExit();


    }


}

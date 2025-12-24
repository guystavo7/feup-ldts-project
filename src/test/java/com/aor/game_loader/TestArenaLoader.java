package com.aor.game_loader;

import com.aor.elements.GameElement;
import com.aor.elements.GroundedSpike;
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
    List<String> SpikeCodes;
    boolean setupOnce=false;
    @BeforeEach
    void setup(){
        if(!setupOnce) {
            TileCodes = new ArrayList<>();
            SpikeCodes=List.of("S");
            arenaLoader = new ArenaLoader();
            for (String s : TileImagePaths.keySet()) {
                if (s.startsWith("T")) {
                    TileCodes.add(s);
                }

            }
            setupOnce=true;
        }
    }


    interface GameElementGenerator{
        GameElement gen(String data,Vector2d pos) throws IOException;
    }

    List<GameElement> ExpectedGameElementListAndFileGenerator(int seed,GameElementGenerator generator,List<String> elementCodes) throws IOException {
        List<GameElement> gameElements=new ArrayList<>();


        File file=new File(testLoaderFilename);
        if(!file.exists()){
            file.createNewFile();
            file.setReadable(true);
            file.setExecutable(true);
        }
//List<String>
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(testLoaderFilename));
        Random random=new Random();
        random.setSeed(seed);
        StringBuilder encodingStringBuilder=new StringBuilder();
        for(int j=0;j<16;j++){
            for(int i=0;i<16;i++){
                int randomId=random.nextInt();
                if(randomId>=0) {
                    randomId%=elementCodes.size();
                    String elementCode=elementCodes.get(randomId);
                    gameElements.add(generator.gen(elementCode,new Vector2d(i * 8, j * 8)));
                    //tiles.add(new Tile(new Vector2d(i * 8, j * 8), TileImagePaths.get(tileCode), GameData.TILE_TAG));
                    encodingStringBuilder.append(elementCode+",");
                }
                else {
                    encodingStringBuilder.append("   ,");
                }

            }
            encodingStringBuilder.append("\n");
        }
        String encoingString=encodingStringBuilder.toString();
        bufferedWriter.write(encoingString);
        bufferedWriter.flush();
        bufferedWriter.close();


        return gameElements;
    }

    public <T> List<T> convert(List<GameElement> elements){
        List<T> res= new ArrayList<>();
        for (GameElement e : elements) {
            res.add((T) e);
        }
        return res ;
    }

    @Property(tries=100)
    void TestTileLoader(@ForAll int seed ) throws IOException {
        setup();
        List<Tile> expectedTiles = convert(ExpectedGameElementListAndFileGenerator(seed,(String data,Vector2d pos)->new Tile(pos, TileImagePaths.get(data), GameData.TILE_TAG),TileCodes));
        List<Tile> tiles= arenaLoader.loadTile16x16file(testLoaderFilename);
         Assertions.assertEquals( tiles,expectedTiles);
    }

    @Property(tries=100)
    void TestGroundedSpikeLoader(@ForAll int seed ) throws IOException {
        setup();
        List<GroundedSpike> expectedSpikes = convert(ExpectedGameElementListAndFileGenerator(seed,(String data, Vector2d pos)->new GroundedSpike(pos),SpikeCodes));
        List<GroundedSpike> spikes= arenaLoader.loadSpike16x16file(testLoaderFilename);
        Assertions.assertEquals( spikes,expectedSpikes);
    }


}

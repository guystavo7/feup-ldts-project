package com.aor.game;

import com.aor.GameLoader.GameLoader;
import com.aor.Tiles.Tile;
import com.aor.components.TransparentSprite;
import com.aor.hero.Hero;
import com.aor.components.BoxCollider;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero hero;
    private List<BoxCollider> walls;
    TransparentSprite ts;
    List<Tile> tiles=new ArrayList<>();

    public Arena() {
        try {
            hero = new Hero(new Vector2d(25, 0), this);
            walls = new ArrayList<>();
            GameLoader gl=new GameLoader();
         //   gl.load16x16file("src/main/java/com/aor/GameLoader/loadTiles1.txt");

            ts=new TransparentSprite(new Vector2d(8,5*8),"src/main/resources/Hero/tile000.png");
            //level 15
            tiles=gl.loadTile16x16file("src/main/java/com/aor/GameLoader/loadTiles1.txt");
            /*
            tiles.add(Tile.TileFrom16x16Grid(0,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(1,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(2,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(3,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(4,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(5,15,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(6,15,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(7,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(8,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(9,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(10,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(11,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(12,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(13,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(14,15,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(15,15,GameDataFeature.tileImgsPath.get(30)));

            //level 14
            tiles.add(Tile.TileFrom16x16Grid(0,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(1,14,GameDataFeature.tileImgsPath.get(46)));
            tiles.add(Tile.TileFrom16x16Grid(2,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(3,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(4,14,GameDataFeature.tileImgsPath.get(31)));


            tiles.add(Tile.TileFrom16x16Grid(7,14,GameDataFeature.tileImgsPath.get(29)));
            tiles.add(Tile.TileFrom16x16Grid(8,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(9,14,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(10,14,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(11,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(12,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(13,14,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(14,14,GameDataFeature.tileImgsPath.get(46)));
            tiles.add(Tile.TileFrom16x16Grid(15,14,GameDataFeature.tileImgsPath.get(30)));


            // level 13

            tiles.add(Tile.TileFrom16x16Grid(0,13,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(1,13,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(2,13,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(3,13,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(4,13,GameDataFeature.tileImgsPath.get(31)));

            tiles.add(Tile.TileFrom16x16Grid(7,13,GameDataFeature.tileImgsPath.get(26)));
            tiles.add(Tile.TileFrom16x16Grid(8,13,GameDataFeature.tileImgsPath.get(28)));


            tiles.add(Tile.TileFrom16x16Grid(11,13,GameDataFeature.tileImgsPath.get(26)));
            tiles.add(Tile.TileFrom16x16Grid(12,13,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(13,13,GameDataFeature.tileImgsPath.get(27)));
            tiles.add(Tile.TileFrom16x16Grid(14,13,GameDataFeature.tileImgsPath.get(30)));
            tiles.add(Tile.TileFrom16x16Grid(15,13,GameDataFeature.tileImgsPath.get(30)));

            // level 12

            tiles.add(Tile.TileFrom16x16Grid(3,12,GameDataFeature.tileImgsPath.get(26)));
            tiles.add(Tile.TileFrom16x16Grid(4,12,GameDataFeature.tileImgsPath.get(28)));


            tiles.add(Tile.TileFrom16x16Grid(7,12,GameDataFeature.tileImgsPath.get(32)));
            tiles.add(Tile.TileFrom16x16Grid(8,12,GameDataFeature.tileImgsPath.get(34)));

            tiles.add(Tile.TileFrom16x16Grid(14,12,GameDataFeature.tileImgsPath.get(29)));
            tiles.add(Tile.TileFrom16x16Grid(15,12,GameDataFeature.tileImgsPath.get(30)));

            //level 11

            tiles.add(Tile.TileFrom16x16Grid(7,11,GameDataFeature.tileImgsPath.get(26)));
            tiles.add(Tile.TileFrom16x16Grid(8,11,GameDataFeature.tileImgsPath.get(28)));

            tiles.add(Tile.TileFrom16x16Grid(14,11,GameDataFeature.tileImgsPath.get(29)));
            tiles.add(Tile.TileFrom16x16Grid(15,11,GameDataFeature.tileImgsPath.get(30)));

            //level 10
            tiles.add(Tile.TileFrom16x16Grid(14,10,GameDataFeature.tileImgsPath.get(29)));
            tiles.add(Tile.TileFrom16x16Grid(15,10,GameDataFeature.tileImgsPath.get(30)));


             */
            for(Tile t:tiles) {
                walls.add(t.getBoxCollider());
            }
            BoxCollider wall1 = new BoxCollider(new Vector2d(0, 128-8), 100, 100);
            walls.add(wall1);
            BoxCollider wall2 = new BoxCollider(new Vector2d(-10, 0), 10, 1000);
            walls.add(wall2);
            BoxCollider wall3 = new BoxCollider(new Vector2d(128, 0), 10, 1000);
            walls.add(wall3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(TextGraphics graphics, float delta) {

        hero.update(graphics, delta);
        ts.draw(graphics);
        for(Tile t:tiles) {
            t.update(graphics, delta);
        }

    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider) {
        List<BoxCollider> colliders = new ArrayList<>();
        Vector2d oldPos = new Vector2d(boxCollider.getPosition().getX(), boxCollider.getPosition().getY());
        boxCollider.setPosition(newPos);

        for (BoxCollider box : walls) {
            if (boxCollider.isColliding(box)) colliders.add(box);
        }

        boxCollider.setPosition(oldPos);
        return colliders;
    }

}

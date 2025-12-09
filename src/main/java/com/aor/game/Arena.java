package com.aor.game;

import com.aor.GameLoader.GameLoader;
import com.aor.gameElements.Strawberry;
import com.aor.tile.Tile;
import com.aor.hero.mvc.Hero;
import com.aor.components.TransparentSprite;
import com.aor.components.BoxCollider;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero hero;
    private List<BoxCollider> walls;
    TransparentSprite ts;
    Strawberry st;
    List<Tile> tiles=new ArrayList<>();
    Integer n_berrys=0;

    public Arena() {
        try {
            hero = new Hero(new Vector2d(25, 0), this);
            walls = new ArrayList<>();
            GameLoader gl = new GameLoader();

            tiles = gl.loadTile16x16file("src/main/java/com/aor/GameLoader/loadTiles1.txt");

            st=new Strawberry(new Vector2d(13*8,3*8));
            for(Tile t : tiles) {
                walls.add(t.getBoxCollider());
            }

            walls.add(st.getBoxCollider());


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
        st.update(graphics,delta);

    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider, String tag) {
        List<BoxCollider> colliders = new ArrayList<>();
        Vector2d oldPos = new Vector2d(boxCollider.getPosition().getX(), boxCollider.getPosition().getY());
        boxCollider.setPosition(newPos);

        for (BoxCollider box : walls) {

            if (boxCollider.isColliding(box)){
                box.firstCollide();
                if (tag.equals("colliders") && !tag.equals(box.getTag())) continue;
                colliders.add(box);
            };

        }

        boxCollider.setPosition(oldPos);
        return colliders;
    }

}

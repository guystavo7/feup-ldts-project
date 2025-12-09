package com.aor.game;

import com.aor.Tiles.Tile;
import com.aor.hero.mvc.Hero;
import com.aor.components.BoxCollider;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero hero;
    private List<BoxCollider> walls;
    Tile t;

    public Arena() {
        try {
            hero = new Hero(new Vector2d(25, 0), this);
            walls = new ArrayList<>();

            t=new Tile(new Vector2d(10,62),"src/main/resources/Tiles/tile012.png");

            walls.add(t.getBoxCollider());
            BoxCollider wall1 = new BoxCollider(new Vector2d(0, 80), new Vector2d(0, 0), 100, 100, "tile");
            walls.add(wall1);
            BoxCollider wall2 = new BoxCollider(new Vector2d(-10, 0), new Vector2d(0, 0), 10, 1000, "tile");
            walls.add(wall2);
            BoxCollider wall3 = new BoxCollider(new Vector2d(90, 0), new Vector2d(0, 0), 10, 1000, "tile");
            walls.add(wall3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(TextGraphics graphics, double delta) {
        hero.update(graphics, delta);
        t.update(graphics,delta);
    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider, String tag) {
        List<BoxCollider> colliders = new ArrayList<>();
        Vector2d oldPos = new Vector2d(boxCollider.getPosition().getX(), boxCollider.getPosition().getY());
        boxCollider.setPosition(newPos);

        for (BoxCollider box : walls) {
            if (!tag.equals("colliders") && !tag.equals(box.getTag())) continue;
            if (boxCollider.isColliding(box)) colliders.add(box);
        }

        boxCollider.setPosition(oldPos);
        return colliders;
    }

}

package com.aor.game;

import com.aor.utils.BoxCollider;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private Hero hero;
    private List<BoxCollider> walls;

    public Arena() {
        try {
            hero = new Hero(new Vector2d(25, 0), this);
            walls = new ArrayList<>();

            BoxCollider wall1 = new BoxCollider(new Vector2d(0, 100), 100, 100);
            walls.add(wall1);
            BoxCollider wall2 = new BoxCollider(new Vector2d(-10, 0), 10, 1000);
            walls.add(wall2);
            BoxCollider wall3 = new BoxCollider(new Vector2d(90, 0), 10, 1000);
            walls.add(wall3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(TextGraphics graphics, float delta) {
        hero.update(graphics, delta);
    }

    public List<BoxCollider> detectCollision(Vector2d newPos, BoxCollider boxCollider) {
        List<BoxCollider> colliders = new ArrayList<>();

        for (BoxCollider box : walls) {
            if (boxCollider.isColliding(newPos, box)) colliders.add(box);
        }

        return colliders;
    }

}

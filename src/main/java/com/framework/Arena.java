package com.framework;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    List<BoxCollider> colliders = new ArrayList<>();

    //Takes a boxCollider and will return a list with all boxCollider which collision happened
    public List<BoxCollider> detectCollision(BoxCollider other) {
        List<BoxCollider> res = new ArrayList<>();

        for (BoxCollider collider : colliders) {
            if (collider.isColliding(other)) res.add(collider);
        }

        return res;
    }

}

package com.aor.utils;

public class GenericView {
    private GenericModel model;
    private Sprite sprite;

    public GenericView(GenericModel model, Vector2d relativePosition, String filePath) {
        try {
            this.model = model;
            sprite = new Sprite(relativePosition, filePath);

        } catch (Exception e) {
            System.out.println("Error loading image");
        }

    }

    public Sprite getSprite() {
        return sprite;
    }

    public GenericModel getModel() {
        return model;
    }
}

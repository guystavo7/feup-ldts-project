package com.aor.hero.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aor.components.Node;
import com.aor.components.sprites.AnimatedSprite;
import com.aor.components.factory.TransparentSpriteFactory;
import com.aor.components.sprites.HairComponent;
import com.aor.components.sprites.HairPos;
import com.aor.components.sprites.TransparentSprite;
import com.aor.hero.states.DashingState;
import com.aor.hero.states.IdleState;
import com.aor.mvc.GenericModel;
import com.aor.mvc.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HeroView implements GenericView {
    private HeroModel model;
    private Map<Class, AnimatedSprite> sprites = new HashMap<>();
    private Map<Class, AnimatedSprite> sprites_v1 = new HashMap<>();
    private AnimatedSprite currentSprite;
    private Class currentState;

    private List<HairComponent> hair=new ArrayList<>();
    private List<HairComponent> hair_v1=new ArrayList<>();


//    private List<AttachedSprite> hair=new ArrayList<>();

    public HeroView(GenericModel model, Map<Class, List<String>> filePaths, Map<Class, List<String>> filesPaths_v1) throws IOException {
        try {
            this.model = (HeroModel) model;
            TransparentSpriteFactory factory = new TransparentSpriteFactory();

            for (Class state : filePaths.keySet()) {
                sprites.put(state, new AnimatedSprite(this.model.getPosition(), filePaths.get(state), 10, factory));
                sprites_v1.put(state, new AnimatedSprite(this.model.getPosition(), filesPaths_v1.get(state), 10, factory));
            }

            currentState = IdleState.class;
            currentSprite = sprites.get(currentState);

            hair.add(new HairComponent(this.model.getPosition(),new Vector2d(3,2),this.model,new Vector2d(4,4),new Vector2d(-4,0),new Vector2d(-2,2),50,"src/main/resources/Hero/hair/hair0.png"));


            hair.add(new HairComponent(hair.get(0).getPosition(),new Vector2d(0,0),hair.get(0),new Vector2d(0,2),new Vector2d(-0.5,0.5),new Vector2d(-0.5,0.5),50,"src/main/resources/Hero/hair/hair1.png"));
            hair.add(new HairComponent(hair.get(1).getPosition(),new Vector2d(0,0),hair.get(1),new Vector2d(0,1),new Vector2d(-0.5,0.5),new Vector2d(-0.5,0.5),50,"src/main/resources/Hero/hair/hair1.png"));

            hair.add(new HairComponent(hair.get(2).getPosition(),new Vector2d(0,0),hair.get(2),new Vector2d(0,1),new Vector2d(0,0),new Vector2d(-0.5,0.5),50,"src/main/resources/Hero/hair/hair2.png"));



            hair_v1.add(new HairComponent(this.model.getPosition(),new Vector2d(3,2),this.model,new Vector2d(4,4),new Vector2d(-4,0),new Vector2d(-2,2),50,"src/main/resources/Hero/hair/hair0_v1.png"));


            hair_v1.add(new HairComponent(hair_v1.get(0).getPosition(),new Vector2d(0,0),hair_v1.get(0),new Vector2d(0,2),new Vector2d(-0.5,0.5),new Vector2d(-0.5,0.5),50,"src/main/resources/Hero/hair/hair1_v1.png"));
            hair_v1.add(new HairComponent(hair_v1.get(1).getPosition(),new Vector2d(0,0),hair_v1.get(1),new Vector2d(0,1),new Vector2d(-0.5,0.5),new Vector2d(-0.5,0.5),50,"src/main/resources/Hero/hair/hair1_v1.png"));

            hair_v1.add(new HairComponent(hair_v1.get(2).getPosition(),new Vector2d(0,0),hair_v1.get(2),new Vector2d(0,1),new Vector2d(0,0),new Vector2d(-0.5,0.5),50,"src/main/resources/Hero/hair/hair2_v1.png"));


            //hair0=new TransparentSprite(this.model.getPosition(),"src/main/resources/Hero/hair/hair0.png");
            //hairPos= new HairPos(new Vector2d(this.model.getPosition()),new Vector2d(2,2),this.model,new Vector2d(4,4), new Vector2d(2,2),50);
//            hair.add(new AttachedSprite(new Sprite(new Vector2d(this.model.getPosition().getX(),this.model.getPosition().getY()),"src/main/resources/hair.png"),this.model ,new Vector2d(0,2),4,4));
//            for(int i=1; i!=3; i++){
//                hair.add(new AttachedSprite(new Sprite(new Vector2d( hair.get(i-1).getPosition().getX(),hair.get(i-1).getPosition().getY()),"src/main/resources/hair.png"),hair.get(i-1),new Vector2d(0,2),4,4));
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnimatedSprite getAnimatedSprite() {
        return currentSprite;
    }

    private void checkState() {
        Map<Class, AnimatedSprite> map;
        if (!model.canDash()) {
            map = sprites_v1;
        } else {
            map = sprites;
        }

        if (currentState != model.getState().getClass()) {
            currentState = model.getState().getClass();
            if (model.getState() instanceof DashingState) currentState = ((DashingState) model.getState()).getLastState();
            currentSprite = map.get(currentState);
        }
    }

    @Override
    public void update(TextGraphics graphics, double delta) {
        checkState();
        currentSprite.update(model.getPosition(), delta);


        if(model.isFacingRight()){
            currentSprite.normal();
//            for (AttachedSprite h:hair){
//                h.normal();
//            }
        }

        else {
            currentSprite.xFlip();
//            for (AttachedSprite h:hair){
//                h.xFlip();
//            }
        }
//        for (AttachedSprite h:hair){
//            h.draw(graphics);
//        }
        for (HairComponent h:hair){
            h.update(delta);
        }
        for (HairComponent h:hair_v1){
            h.update(delta);
        }


        if (!model.canDash()) {
            for (HairComponent h:hair_v1){
                h.draw(graphics);
            }
        } else {
            for (HairComponent h:hair){
                h.draw(graphics);
            }
        }


        currentSprite.draw(graphics);


    }
}
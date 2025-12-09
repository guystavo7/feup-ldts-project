package com.aor.hero.mvc;

import java.util.ArrayList;
import java.util.List;

import com.aor.components.AnimatedSprite;
import com.aor.components.AttachedSprite;
import com.aor.components.Sprite;
import com.aor.components.TransparentSpriteFactory;
import com.aor.mvc.GenericModel;
import com.aor.mvc.GenericView;
import com.aor.utils.Vector2d;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HeroView implements GenericView {
    private HeroModel model;
    private AnimatedSprite animatedSprite;
//    private List<AttachedSprite> hair=new ArrayList<>();

    public HeroView(GenericModel model, List<String> filePaths) {
        try {
            this.model = (HeroModel) model;
            animatedSprite = new AnimatedSprite(this.model.getPosition(), filePaths, 10,new TransparentSpriteFactory());
//            hair.add(new AttachedSprite(new Sprite(new Vector2d(this.model.getPosition().getX(),this.model.getPosition().getY()),"src/main/resources/hair.png"),this.model ,new Vector2d(0,2),4,4));
//            for(int i=1; i!=3; i++){
//                hair.add(new AttachedSprite(new Sprite(new Vector2d( hair.get(i-1).getPosition().getX(),hair.get(i-1).getPosition().getY()),"src/main/resources/hair.png"),hair.get(i-1),new Vector2d(0,2),4,4));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }

    @Override
    public void update(TextGraphics graphics, double delta) {

        animatedSprite.update(model.getPosition(), delta);

        if(model.getVelocity().getX()>0){
            animatedSprite.normal();
//            for (AttachedSprite h:hair){
//                h.normal();
//            }
        }

        else if(model.getVelocity().getX()<0){
            animatedSprite.xFlip();
//            for (AttachedSprite h:hair){
//                h.xFlip();
//            }
        }
//        for (AttachedSprite h:hair){
//            h.draw(graphics);
//        }
        animatedSprite.draw(graphics);

    }
}
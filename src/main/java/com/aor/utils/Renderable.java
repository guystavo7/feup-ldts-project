package code.org.mcode;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Renderable {
    private Vec2d pos;
    private Vec2d Lpos;
  //  private Integer width,height;
    private List<MImage> frames=new ArrayList<MImage>();
    private Integer index=0;
    private Byte drawFlag=0;

    public  Renderable(Vec2d pos, String filepath)throws IOException {
        Init(pos,new MImage(filepath));
    }
    public  Renderable(Vec2d pos, MImage img)throws IOException {
        Init(pos,img);
    }
    private void Init(Vec2d pos,MImage img){
        this.pos=pos;
        Lpos=new Vec2d(pos);
        this.frames.add(img);
    }

    public final int getFramesSize(){return frames.size();}
    public final void selectFrame(int index){
        this.index=index;
    }
    public final void addFrame(MImage img){
        this.frames.add(img);
    }
    public final boolean removeFrame(MImage img){
        return this.frames.remove(img);
    }
    public final int getFrameIndex(String filepath){
        for(int i =0;i<frames.size();i++){
            if(frames.get(i).getFilepath().equals(filepath)){
                return i;
            }
        }
        return -1;
    }
    public final void draw(TextGraphics graphics){
        frames.get(index).draw(graphics,pos);
        Lpos.set(pos);
        if(drawFlag==1){
            drawFlag++;
        }
    }
    public final void stopDrawing(){
        drawFlag=1;
    }
    public final void startDrawing(){
        drawFlag=0;
    }
    public final void setPos(Vec2d pos){
        this.pos.set(pos);
    }
    public final Byte getFlag(){
        return drawFlag;
    }
    public final Vec2d getPos(){
        return pos;
    }
    public final Vec2d getLPos(){
        return Lpos;
    }
    public final Integer getHeight() {
        return frames.get(index).getHeight();
    }
    public final Integer getWidth(){
        return frames.get(index).getWidth();
    }
}

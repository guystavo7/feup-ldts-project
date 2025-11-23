package code.org.mcode;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class MImage {
    private String filepath; // TODO DELETE
    private Integer width,height;
    private List<List<Pixel>> pixeis;
    private Integer color=null;

    public MImage(int width,int height, int color){
        filepath=null;
        this.width=width;
        this.height=height;

        pixeis=new ArrayList<List<Pixel>>();
        for (int y = 0; y < height; y++) {
            pixeis.add(new ArrayList<Pixel>());
            for (int x = 0; x < width; x++) {
                pixeis.get(y).add(new Pixel(color));
            }
        }
        this.color=color;
    }
    public MImage(String filepath) throws IOException{
        File file=new File(filepath);
        this.filepath=filepath;

        //File file=new File("/java/org/mcode/pixelw.png");

        //   File file=new File("/java/org/mcode/pixelw.png");

        BufferedImage image = ImageIO.read(file);
        width=image.getWidth();
        height=image.getHeight();

        pixeis=new ArrayList<List<Pixel>>();
        for (int y = 0; y < height; y++) {
            pixeis.add(new ArrayList<Pixel>());
            for (int x = 0; x < width; x++) {
                pixeis.get(y).add(new Pixel(image.getRGB(x,y)));
            }
        }
    }
    public final void draw(TextGraphics graphics){
        draw(graphics,new Vec2d(0,0));
    }
    public final void draw(TextGraphics graphics,final Vec2d pos){
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++) {
                if (getPixel(x,y).getAlpha()==255) {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(getPixel(x, y).getRgb_color()));
                    graphics.fillRectangle(new TerminalPosition(x + pos.x, y + pos.y), new TerminalSize(1, 1), ' ');

                }
            }
        }
    }


    public String toString(){
        return pixeis.toString();
    }

    //public List<List<Pixel>> getPixeis() {
    //    return pixeis;
    //}
    public Pixel getPixel(int x,int y){
        return pixeis.get(y).get(x);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public String getFilepath(){return filepath;}
    public Integer getColor(){return color;}
    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        MImage p = (MImage) o;
        return filepath == p.getFilepath() && color == p.getColor();
    }
}

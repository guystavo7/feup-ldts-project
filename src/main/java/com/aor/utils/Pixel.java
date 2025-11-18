package code.org.mcode;

import java.util.List;

public class Pixel {
    private String argb_color;
    private String rgb_color;
    private Short alpha;

    public Pixel(Integer color){
        argb_color="#".concat(String.format("%08x", color));
        rgb_color="#".concat(argb_color.substring(3));
        alpha= (short) (color>>24 & 0xFF);
    }
   // @override
    public String toString(){

        return  rgb_color;
    }
    public String getRgb_color(){
        return  rgb_color;
    }
    public String getArgb_color(){
        return  argb_color;
    }
    public short getAlpha(){return alpha;}
}

package com.aor.utils;

public class Pixel {
    private String argbColor;
    private String rgbColor;
    private Short alpha;

    public Pixel(Integer color){
        argbColor = "#".concat(String.format("%08x", color));
        rgbColor = "#".concat(argbColor.substring(3));
        alpha = (short) (color>>24 & 0xFF);
    }
   // @override
    public String toString(){
        return  rgbColor;
    }

    public String getRGBColor(){
        return  rgbColor;
    }
    public String getARGBColor(){
        return  argbColor;
    }
    public short getAlpha(){return alpha;}
}

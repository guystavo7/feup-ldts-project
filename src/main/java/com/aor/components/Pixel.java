package com.aor.components;

public class Pixel {
    private String argbColor;
    private String rgbColor;
    private Short alpha;

    public Pixel(Integer color){
        argbColor = "#".concat(String.format("%08x", color));
        rgbColor = "#".concat(argbColor.substring(3));
        alpha = (short) (color>>24 & 0xFF);
        //alpha= (short)(("#000000".equals(rgbColor))?0:0xFF);
    }

    public String toString(){
        return  rgbColor;
    }

    public String getArgbColor(){
        return argbColor;
    }

    public Short getAlpha() {
        return alpha;
    }
}

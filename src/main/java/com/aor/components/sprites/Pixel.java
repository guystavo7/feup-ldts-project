package com.aor.components.sprites;

public class Pixel {
    private final String argbColor;
    private final String rgbColor;
    private final Short alpha;

    public Pixel(Integer color){
        argbColor = "#".concat(String.format("%08x", color));
        rgbColor = "#".concat(argbColor.substring(3));
        alpha = (short) (color>>24 & 0xFF);
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

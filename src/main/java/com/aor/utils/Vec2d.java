package code.org.mcode;

public class Vec2d {
    public Integer x,y;
    public Vec2d(Integer x,Integer y){
        this.x=x;
        this.y=y;
    }
    public Vec2d(Vec2d pos){
        this.x=pos.x;
        this.y=pos.y;
    }
    public void set(Vec2d pos){
        this.x=pos.x;
        this.y=pos.y;
    }
    public void set(Integer x,Integer y){
        this.x=x;
        this.y=y;
    }
}

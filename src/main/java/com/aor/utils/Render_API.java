package code.org.mcode;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Render_API {
    int winWidth,winHeight;
    MImage background;

    Terminal terminal;
    Screen screen;
    TextGraphics graphics;


    final List<List<Renderable>> layers=new ArrayList<List<Renderable>>();;

    boolean exit=false;

   // Render_API(int width,int height){

    //}
    public  Render_API(int width,int height,MImage background_,int fontSize){
        try {

            layers.add(new ArrayList<>());

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
          //  Font.createFont(, )
            Font myFont = new Font("Monospaced", Font.PLAIN, fontSize);
            AWTTerminalFontConfiguration myFontConfiguration = AWTTerminalFontConfiguration.newInstance(myFont);


            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setTerminalEmulatorFontConfiguration(myFontConfiguration);

            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();

            graphics = screen.newTextGraphics();

            background=background_;



            background.draw(graphics);


            //screen.setCharacter(10, 10, TextCharacter.fromCharacter('■')[0]);
            //screen.setCharacter(11, 10, TextCharacter.fromCharacter('■')[0]);

            screen.refresh();

            // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public final void close() {
        try {
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final int getWinWidth(){
        return winWidth;
    }
    public final int getWinHeight(){
        return winHeight;
    }
    public final boolean render()throws IOException{
        KeyStroke key =readKey();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            exit=true;
        }
        if(!exit){
            for (List<Renderable> layer:layers){
                for(Renderable renderable:layer){
                    if(renderable.getFlag()<=1){
                        drawBackgroundOn(renderable);
                    }
                }
            }
            for (List<Renderable> layer:layers){
                for(Renderable renderable:layer){
                    if(renderable.getFlag()==0){
                        renderable.draw(graphics);
                    }
                }
            }
            screen.refresh();
        }
        else {
            close();
        }
        return exit;
    }
    public final KeyStroke readKey()throws IOException{
        return screen.readInput();
    }

    public final Integer createLayer(){
        layers.add(new ArrayList<>());
        return layers.size()-1;
    }

    public final void addRenderable(Renderable renderable){
        addRenderable(renderable, 0);
    }
    public final void addRenderable(Renderable renderable,Integer layer){
        layers.get(layer).add(renderable);
    }
    private final void drawBackgroundOn(Renderable renderable){
        Vec2d pos=renderable.getLPos();
        Integer width=renderable.getWidth();
        Integer height=renderable.getHeight();

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++) {
                if(0<x+ pos.x && x+ pos.x<background.getWidth() && 0< y+ pos.y&& y+ pos.y<background.getHeight()){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(background.getPixel(x+ pos.x,y+ pos.y).getRgb_color()));
                    graphics.fillRectangle(new TerminalPosition(x+ pos.x, y+ pos.y), new TerminalSize(1,1), ' ');
                }
            }
        }
    }
}

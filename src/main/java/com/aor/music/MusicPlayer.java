package com.aor.music;

import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {
    String filePath;
    Clip clip;
    public MusicPlayer(String filePath) {
        try {
            File arquivo = new File(filePath);
            this.filePath=filePath;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public void close(){
        clip.close();
    }

}




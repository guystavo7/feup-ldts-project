package com.aor.music;

import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer implements AutoCloseable {
    private Clip clip;

    public MusicPlayer() {
        try {
            clip = AudioSystem.getClip();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMusic(String path, boolean loop, boolean autoStart) {
        try {
            this.close();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip.open(audioStream);

            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            if (autoStart) start();
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

    @Override
    public void close() throws Exception {
        if (clip.isRunning()) clip.stop();
        clip.close();
    }

}




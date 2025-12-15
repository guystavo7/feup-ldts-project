package com.aor.music;

import com.aor.game.Game;
import com.aor.game.GameData;

import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer implements AutoCloseable {
    private Clip clip;

    public MusicPlayer(String path, boolean loop, boolean autoStart) {
        try {
            clip = AudioSystem.getClip();
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(GameData.sfxFiles.get(path));
                clip.open(audioStream);

                if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
                if (autoStart) start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        clip.setFramePosition(0);
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




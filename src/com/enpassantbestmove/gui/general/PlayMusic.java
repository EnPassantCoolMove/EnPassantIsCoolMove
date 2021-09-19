package com.enpassantbestmove.gui.general;

import javax.sound.sampled.*;
import java.net.URL;

public class PlayMusic{

    public static void play(URL soundURL) {
        try {
            var audioStream = AudioSystem.getAudioInputStream(soundURL);
            var clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

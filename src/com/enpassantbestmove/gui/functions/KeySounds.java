package com.enpassantbestmove.gui.functions;

import com.enpassantbestmove.gui.GUISounds;
import com.enpassantbestmove.gui.general.PlayMusic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeySounds implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        PlayMusic.play(GUISounds.keyPressSoundURL);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

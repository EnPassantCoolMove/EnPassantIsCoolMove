package com.enpassantbestmove.gui.functions;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MaximumSize implements KeyListener {
    private JTextComponent textComponent;
    private int maximumSize;

    public MaximumSize(JTextComponent textComponent, int maximumSize) {
        this.textComponent = textComponent;
        this.maximumSize = maximumSize;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (textComponent.getText().length() >= maximumSize) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

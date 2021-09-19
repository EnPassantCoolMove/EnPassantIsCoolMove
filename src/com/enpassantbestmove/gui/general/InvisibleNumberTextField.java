package com.enpassantbestmove.gui.general;

import com.enpassantbestmove.gui.GUIColors;
import com.enpassantbestmove.gui.functions.KeySounds;
import com.enpassantbestmove.gui.functions.MaximumSize;
import com.enpassantbestmove.gui.functions.OnlyNumbers;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class InvisibleNumberTextField extends JTextField implements KeyListener {
    private final InvisibleLabel foregroundText;

    public InvisibleNumberTextField(JFrame frame, int textSize, int x, int y, int width, int height) {
        foregroundText = new InvisibleLabel(textSize, x - 3, y, width, height);
        foregroundText.setFont(new Font("Candera", Font.PLAIN, textSize));
        foregroundText.setForeground(GUIColors.OFF_WHITE);
        frame.add(foregroundText);

        this.setBounds(x, y, width, height);
        this.setFont(new Font("Candera", Font.PLAIN, textSize));
        this.setForeground(Color.BLACK);
        this.setCaretColor(GUIColors.OFF_WHITE);
        this.addKeyListener(new OnlyNumbers());
        this.addKeyListener(new MaximumSize(this, 4));
        this.addKeyListener(new KeySounds());
        this.addKeyListener(this);
        this.setOpaque(false);
        this.setBorder(null);
    }

    public InvisibleNumberTextField(JPanel panel, int textSize, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Candera", Font.PLAIN, textSize));
        this.setForeground(Color.BLACK);
        this.setCaretColor(GUIColors.OFF_WHITE);
        this.addKeyListener(new OnlyNumbers());
        this.addKeyListener(new MaximumSize(this, 4));
        this.addKeyListener(this);
        this.setOpaque(false);
        this.setBorder(null);

        foregroundText = new InvisibleLabel(textSize, x + 3, y, width, height);
        panel.add(foregroundText);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        updateForegroundTextAfter(2);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public void updateForegroundTextAfter(int milliseconds) {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        foregroundText.setText(InvisibleNumberTextField.this.getText());
                    }
                },
                milliseconds
        );
    }
}

package com.enpassantbestmove.gui.sidebar;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class AnimatedClock extends JLabel implements Runnable{
    private final BufferedImage clockSpriteSheet;
    private int column = 1;
    private int tenthOfSeconds = 9;

    public AnimatedClock(BufferedImage clockSpriteSheet, int x, int y) {
        this.setBounds(x, y, 61, 62);
        this.clockSpriteSheet = clockSpriteSheet;
        this.setIcon(new ImageIcon(clockSpriteSheet.getSubimage(0, 0, 61, 62)));
    }

    public void update() {
        tenthOfSeconds++;
        if (tenthOfSeconds == 10) {
            tenthOfSeconds = 0;
            this.setIcon(new ImageIcon(clockSpriteSheet.getSubimage(column++ * 61, 0, 61, 62)));
            if (column > 7)
                column = 0;
        }
    }

    @Override
    public void run() {
        update();
    }
}

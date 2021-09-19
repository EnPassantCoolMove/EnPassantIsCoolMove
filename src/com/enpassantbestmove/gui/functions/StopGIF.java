package com.enpassantbestmove.gui.functions;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class StopGIF implements ImageObserver {
    private final JLabel label;
    private int cycles = 0;
    private int maxCyclesAllowed;

    public StopGIF(JLabel label, int maxCyclesAllowed) {
        this.label = label;
        this.maxCyclesAllowed = maxCyclesAllowed;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if (infoflags == FRAMEBITS) {
            cycles++;
            label.repaint();
        }
        return cycles - 1 != maxCyclesAllowed;
    }
}

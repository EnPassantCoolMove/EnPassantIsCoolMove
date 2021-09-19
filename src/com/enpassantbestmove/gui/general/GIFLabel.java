package com.enpassantbestmove.gui.general;

import com.enpassantbestmove.gui.functions.StopGIF;

import javax.swing.*;

public class GIFLabel extends JLabel {

    public GIFLabel(ImageIcon GIF, int maxCyclesAllowed, int x, int y, int width, int height) {
        this.setIcon(GIF);
        this.setBounds(x, y, width, height);
        GIF.setImageObserver(new StopGIF(this, maxCyclesAllowed));
    }
}

package com.enpassantbestmove.gui.general;

import javax.swing.*;
import java.awt.*;

public class BlurLabel extends JLabel {

    public BlurLabel(int magnitude, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(0, 0, 0, magnitude));
        this.setOpaque(true);
    }
}

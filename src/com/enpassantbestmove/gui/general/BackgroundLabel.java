package com.enpassantbestmove.gui.general;

import javax.swing.*;

public class BackgroundLabel extends JLabel {

    public BackgroundLabel(ImageIcon icon, int x, int y) {
        this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.setIcon(icon);
    }
}

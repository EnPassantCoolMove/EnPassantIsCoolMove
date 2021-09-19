package com.enpassantbestmove.gui.general;

import javax.swing.*;
import java.awt.*;

public class InvisibleLabel extends JLabel {

    public InvisibleLabel(int textSize, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Candera", Font.PLAIN, textSize));
    }
}

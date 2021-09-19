package com.enpassantbestmove.gui.general;

import com.enpassantbestmove.gui.GUIColors;

import javax.swing.*;
import java.awt.*;

public class GUILabel extends InvisibleLabel {
    private InvisibleLabel foregroundText;

    public GUILabel(JFrame frame, String text, int textSize, int betweenTextSize, int x, int y, int width, int height) {
        super(textSize, x, y, width, height);
        this.setHorizontalAlignment(CENTER);
        this.setForeground(Color.BLACK);
        this.setText(text);

        foregroundText = new InvisibleLabel(textSize, x - betweenTextSize, y, width, height);
        foregroundText.setHorizontalAlignment(CENTER);
        foregroundText.setForeground(GUIColors.OFF_WHITE);
        foregroundText.setText(text);
        frame.add(foregroundText);
    }

    public GUILabel(JPanel panel, String text, int textSize, int betweenTextSize, int x, int y, int width, int height) {
        super(textSize, x, y, width, height);
        this.setHorizontalAlignment(CENTER);
        this.setForeground(Color.BLACK);
        this.setText(text);

        foregroundText = new InvisibleLabel(textSize, x - betweenTextSize, y, width, height);
        foregroundText.setHorizontalAlignment(CENTER);
        foregroundText.setForeground(GUIColors.OFF_WHITE);
        foregroundText.setText(text);
        panel.add(foregroundText);
    }

    public InvisibleLabel getForegroundText() {
        return foregroundText;
    }
}

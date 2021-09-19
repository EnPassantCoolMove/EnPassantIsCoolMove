package com.enpassantbestmove.gui.sidebar;

import com.enpassantbestmove.gamerules.AdvantageCalculator;
import com.enpassantbestmove.gui.GUIColors;

import javax.swing.*;
import java.awt.*;

public class AdvantageBar extends JProgressBar {

    public AdvantageBar() {
        super(VERTICAL, 0, AdvantageCalculator.getTotalPieceValue());
        this.setValue(AdvantageCalculator.getTotalWhitePieceValue());

        this.setBounds(3, 3, 10, 475);
        this.setBackground(Color.BLACK);
        this.setForeground(GUIColors.OFF_WHITE);
        this.setBorderPainted(false);
    }
}

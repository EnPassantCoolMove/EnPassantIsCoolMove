package com.enpassantbestmove.gui.sidebar;

import com.enpassantbestmove.gamerules.AdvantageCalculator;

import javax.swing.*;
import java.awt.*;

public class AdvantageDisplay extends JLabel {

    public AdvantageDisplay() {
        this.setBounds(20, 220, 80, 40);
        this.setFont(new Font("Candera", Font.PLAIN, 25));
        AdvantageCalculator.updateStart(this);
    }
}

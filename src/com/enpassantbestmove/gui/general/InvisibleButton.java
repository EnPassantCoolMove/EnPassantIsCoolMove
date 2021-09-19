package com.enpassantbestmove.gui.general;

import com.enpassantbestmove.gui.GUISounds;
import com.enpassantbestmove.gui.functions.DimWhenMouseHovers;

import javax.swing.*;
import java.awt.event.ActionListener;

// creates an invisible button

public class InvisibleButton extends JButton {

    public InvisibleButton (int x, int y, int width, int height, ActionListener actionListener) {
        this.setBounds(x, y, width, height);
        this.addActionListener(actionListener);
        this.addActionListener(e -> PlayMusic.play(GUISounds.buttonClickSoundURL));
        this.setFocusable(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    public InvisibleButton (ImageIcon iconIdle, ImageIcon iconPressed, int x, int y, ActionListener actionListener) {
        this.setBounds(x, y, iconIdle.getIconWidth(), iconIdle.getIconHeight());
        this.setIcon(iconIdle);
        this.setDisabledIcon(iconIdle);
        this.addActionListener(actionListener);
        this.addActionListener(e -> PlayMusic.play(GUISounds.buttonClickSoundURL));
        this.addMouseListener(new DimWhenMouseHovers(this, iconIdle, iconPressed));
        this.setFocusable(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}

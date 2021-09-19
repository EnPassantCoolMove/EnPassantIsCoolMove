package com.enpassantbestmove.gui.functions;

import com.enpassantbestmove.gui.general.InvisibleButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DimWhenMouseHovers implements MouseListener {
    private InvisibleButton currentButton;
    private ImageIcon iconIdle;
    private ImageIcon iconPressed;

    public DimWhenMouseHovers(InvisibleButton currentButton, ImageIcon iconIdle, ImageIcon iconPressed) {
        this.currentButton = currentButton;
        this.iconIdle = iconIdle;
        this.iconPressed = iconPressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        currentButton.setIcon(iconPressed);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentButton.setIcon(iconIdle);
    }
}

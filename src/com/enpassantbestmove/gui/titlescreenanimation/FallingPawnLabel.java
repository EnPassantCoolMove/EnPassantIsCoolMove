package com.enpassantbestmove.gui.titlescreenanimation;

import javax.swing.*;
import java.awt.*;

public class FallingPawnLabel extends JLabel {
    private ImageIcon icon;

    private double xCoord;
    private double yCoord;
    private double xSpeed;
    private double ySpeed;

    private double rotationPosition;
    private double rotationSpeed;

    private double alphaValue;
    private double alphaValueFade;

    public FallingPawnLabel(ImageIcon icon) {
        reset();
        this.icon = icon;
        this.setBounds((int) xCoord, (int) yCoord, icon.getIconWidth(), icon.getIconHeight());
    }

    public void fall() {
        if (alphaValue - alphaValueFade < 0) reset();
        alphaValue -= alphaValueFade;
        this.setBounds((int) (xCoord += xSpeed), (int) (yCoord += ySpeed), icon.getIconWidth(), icon.getIconHeight());
    }

    public void reset() {
        xCoord = Math.random() * 780;
        yCoord = -51;
        xSpeed = Math.random() * 2 - 1;
        ySpeed = Math.random() + 0.5;

        rotationPosition = Math.random() * 2 * Math.PI;
        rotationSpeed = Math.random() / 10 - 0.05;

        alphaValue = 0.7;
        alphaValueFade = Math.random() / 500 + 0.0025;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alphaValue));
        g2.rotate(rotationPosition += rotationSpeed, icon.getIconWidth() / 2.0, icon.getIconHeight() / 2.0);
        g2.drawImage(icon.getImage(), 0, 0, null);
    }
}

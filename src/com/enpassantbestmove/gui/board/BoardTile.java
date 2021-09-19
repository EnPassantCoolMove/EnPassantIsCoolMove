package com.enpassantbestmove.gui.board;

import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.selection.PieceInteraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// creates a board tile

public class BoardTile extends JButton implements ActionListener{
    private final int xCoord;
    private final int yCoord;
    private final Image selectionIndicator;
    private final Image movementIndicator;
    private final Image attackIndicator;
    private final Image enPassantIndicator;
    private final Image checkIndicator;

    private Piece piece;

    private boolean selectionIndicatorOn = false;
    private boolean movementIndicatorOn = false;
    private boolean attackIndicatorOn = false;
    private boolean enPassantIndicatorOn = false;
    private boolean checkIndicatorOn = false;

    public BoardTile(int xCoord, int yCoord, Piece piece, ImageIcon selectionIndicator, ImageIcon movementIndicator, ImageIcon attackIndicator, ImageIcon enPassantIndicator, ImageIcon checkIndicator) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.piece = piece;
        this.selectionIndicator = selectionIndicator.getImage();
        this.movementIndicator = movementIndicator.getImage();
        this.attackIndicator = attackIndicator.getImage();
        this.enPassantIndicator = enPassantIndicator.getImage();
        this.checkIndicator = checkIndicator.getImage();

        this.addActionListener(this);
        this.setLayout(null);
        this.setFocusable(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    // updates what is on tile
    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if (piece != null) {
            if (checkIndicatorOn) {
                if (piece.getColor() == PieceColor.BLACK)
                    g2D.drawImage(new ImageIcon(getClass().getClassLoader().getResource("pieces/black/BlackKingChecked.gif")).getImage(), 0, 0, null);
                else
                    g2D.drawImage(new ImageIcon(getClass().getClassLoader().getResource("pieces/white/WhiteKingChecked.gif")).getImage(), 0, 0, null);
            }
            else
                g2D.drawImage(piece.getPieceIcon().getImage(), 0, 0, null);
        }

        if (selectionIndicatorOn)
            g2D.drawImage(selectionIndicator, 0, 0, null);
        else if (movementIndicatorOn)
            g2D.drawImage(movementIndicator, 0, 0, null);
        else if (attackIndicatorOn)
            g2D.drawImage(attackIndicator, 0, 0, null);
        else if (enPassantIndicatorOn)
            g2D.drawImage(enPassantIndicator, 0, 0, null);

        if (checkIndicatorOn)
            g2D.drawImage(checkIndicator, 0, 0, null);
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isSelectionIndicatorOn() {
        return selectionIndicatorOn;
    }

    public boolean isMovementIndicatorOn() {
        return movementIndicatorOn;
    }

    public boolean isAttackIndicatorOn() {
        return attackIndicatorOn;
    }

    public boolean isEnPassantIndicatorOn() {
        return enPassantIndicatorOn;
    }

    public boolean isCheckIndicatorOn() {
        return checkIndicatorOn;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setSelectionIndicator(boolean selectionIndicatorOn) {
        this.selectionIndicatorOn = selectionIndicatorOn;
    }

    public void setMovementIndicator(boolean movementIndicatorOn) {
        this.movementIndicatorOn = movementIndicatorOn;
    }

    public void setAttackIndicator(boolean attackIndicatorOn) {
        this.attackIndicatorOn = attackIndicatorOn;
    }

    public void setEnPassantIndicator(boolean enPassantIndicatorOn) {
        this.enPassantIndicatorOn = enPassantIndicatorOn;
    }

    public void setCheckIndicator(boolean checkIndicatorOn) {
        this.checkIndicatorOn = checkIndicatorOn;
    }

    // what to do when this tile is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        new PieceInteraction(xCoord, yCoord).select();
    }
}

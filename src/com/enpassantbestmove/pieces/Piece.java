package com.enpassantbestmove.pieces;

import javax.swing.*;

// abstract class which all pieces inherit from

public abstract class Piece {

    public abstract PieceType getType();

    public abstract int getPieceValue();

    public abstract ImageIcon getPieceIcon();

    public abstract PieceColor getColor();

    public abstract int getXCoord();

    public abstract int getYCoord();

    public abstract int getMoves();

    public abstract int getMovesSinceFirstMove();

    public abstract boolean canMove(Piece[][] pieceLayout, int xCoord, int yCoord);

    public abstract boolean canTake(Piece[][] pieceLayout, int xCoord, int yCoord);

    public abstract Piece getCopy();

    public abstract void setXCoord(int xCoord);

    public abstract void setYCoord(int yCoord);

    public abstract void incrementMoves();

    public abstract void incrementMovesSinceFirstMove();

    public boolean canEnpassant(Piece[][] pieceLayout, int xCoord, int yCoord) {
        return false;
    }

    public boolean canCastle(Piece[][] pieceLayout, int xCoord, int yCoord) {
        return false;
    }
}

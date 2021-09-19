package com.enpassantbestmove.pieces;

import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedFuture;
import com.enpassantbestmove.movementvalidation.movementblockers.NoEnPassantAvailable;
import com.enpassantbestmove.movementvalidation.movementblockers.NoFriendlyFire;
import com.enpassantbestmove.movementvalidation.movementblockers.PieceOnTile;
import com.enpassantbestmove.movementvalidation.piecemovement.CollisionValidation;
import com.enpassantbestmove.movementvalidation.piecemovement.DiagonalValidation;
import com.enpassantbestmove.movementvalidation.piecemovement.DistanceValidation;
import com.enpassantbestmove.movementvalidation.piecemovement.EnPassantValidation;
import com.enpassantbestmove.movementvalidation.submovement.VerticalValidation;

import javax.swing.*;

public class Pawn extends Piece{
    final private PieceType type = PieceType.PAWN;
    final private int value = 1;
    final private PieceColor color;

    private int xCoord;
    private int yCoord;
    private int moves = 0;
    private int movesSinceFirstMove = 0;
    private boolean isSelected = false;

    public Pawn(PieceColor color) {
        this.color = color;
    }

    public Pawn(PieceColor color, int xCoord, int yCoord) {
        this.color = color;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public int getPieceValue() {
        return value;
    }

    @Override
    public ImageIcon getPieceIcon() {
        return color == PieceColor.WHITE ? new ImageIcon(getClass().getClassLoader().getResource("pieces/white/WhitePawn.png"))
                : new ImageIcon(getClass().getClassLoader().getResource("pieces/black/BlackPawn.png"));
    }

    public PieceColor getColor() {
        return color;
    }

    @Override
    public int getXCoord() {
        return xCoord;
    }

    @Override
    public int getYCoord() {
        return yCoord;
    }

    @Override
    public int getMoves() {
        return moves;
    }

    @Override
    public int getMovesSinceFirstMove() {
        return movesSinceFirstMove;
    }

    @Override
    public boolean canMove(Piece[][] pieceLayout, int xCoord, int yCoord) {
        if(moves == 0)
            return VerticalValidation.validate(this, xCoord)
                    && DistanceValidation.validateFront(2, this, yCoord)
                    && CollisionValidation.validate(pieceLayout, this, xCoord, yCoord)
                    && NoFriendlyFire.validate(pieceLayout, this, xCoord, yCoord)
                    && NoEnPassantAvailable.validate(color)
                    && KingNotCheckedFuture.validate(this, xCoord, yCoord)
                    && !PieceOnTile.validate(pieceLayout, xCoord, yCoord);
        else
            return VerticalValidation.validate(this, xCoord)
                    && DistanceValidation.validateFront(1, this, yCoord)
                    && NoFriendlyFire.validate(pieceLayout, this, xCoord, yCoord)
                    && NoEnPassantAvailable.validate(color)
                    && KingNotCheckedFuture.validate(this, xCoord, yCoord)
                    && !PieceOnTile.validate(pieceLayout, xCoord, yCoord);
    }

    @Override
    public boolean canTake(Piece[][] pieceLayout, int xCoord, int yCoord) {
        if (PieceOnTile.getPiece(pieceLayout, xCoord, yCoord).getType() == PieceType.KING)
            return DiagonalValidation.validate(this, xCoord, yCoord)
                    && DistanceValidation.validateFront(1, this, yCoord)
                    && NoFriendlyFire.validate(pieceLayout, this, xCoord, yCoord)
                    && NoEnPassantAvailable.validate(color);
        else
            return DiagonalValidation.validate(this, xCoord, yCoord)
                    && DistanceValidation.validateFront(1, this, yCoord)
                    && NoFriendlyFire.validate(pieceLayout, this, xCoord, yCoord)
                    && NoEnPassantAvailable.validate(color)
                    && KingNotCheckedFuture.validate(this, xCoord, yCoord)
                    && PieceOnTile.validate(pieceLayout, xCoord, yCoord);
    }

    @Override
    public Piece getCopy() {
        var copy = new Pawn(color);
        copy.xCoord = xCoord;
        copy.yCoord = yCoord;
        copy.isSelected = isSelected;
        copy.moves = moves;
        copy.movesSinceFirstMove = movesSinceFirstMove;

        return copy;
    }

    @Override
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    @Override
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public void incrementMoves() {
        moves++;
    }

    @Override
    public void incrementMovesSinceFirstMove() {
        movesSinceFirstMove++;
    }

    @Override
    public boolean canEnpassant(Piece[][] pieceLayout, int xCoord, int yCoord) {
        if (EnPassantValidation.validateLeft(pieceLayout, this))
            return DiagonalValidation.validate(this, xCoord, yCoord)
                    && DistanceValidation.validateFront(1, this, yCoord)
                    && DistanceValidation.validateLeft(1, this, xCoord);
        else if (EnPassantValidation.validateRight(pieceLayout, this))
            return DiagonalValidation.validate(this, xCoord, yCoord)
                    && DistanceValidation.validateFront(1, this, yCoord)
                    && DistanceValidation.validateRight(1, this, xCoord);
        return false;
    }
}

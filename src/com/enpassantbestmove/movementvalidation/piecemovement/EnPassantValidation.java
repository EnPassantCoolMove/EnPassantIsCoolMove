package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

// checks if en passant is possible

public class EnPassantValidation {

    public static boolean validateLeft(Piece[][] pieceLayout, Piece previousSelection) {
        Piece leftPiece = null;
        try {
            leftPiece = pieceLayout[previousSelection.getXCoord() - 1][previousSelection.getYCoord()];
        } catch (Exception ignored) {}

        if (leftPiece != null)
            if (leftPiece.getColor() != previousSelection.getColor() && leftPiece.getType() == PieceType.PAWN && leftPiece.getMoves() == 1 && leftPiece.getMovesSinceFirstMove() == 1) {
                if (leftPiece.getColor() == PieceColor.BLACK)
                    return (leftPiece.getYCoord() == 3);
                else
                    return (leftPiece.getYCoord() == 4);
            }
        return false;
    }

    public static boolean validateRight(Piece[][] pieceLayout, Piece previousSelection) {
        Piece rightPiece = null;
        try {
            rightPiece = pieceLayout[previousSelection.getXCoord() + 1][previousSelection.getYCoord()];
        } catch (Exception ignored) {}

        if (rightPiece != null)
            if (rightPiece.getColor() != previousSelection.getColor() && rightPiece.getType() == PieceType.PAWN && rightPiece.getMoves() == 1 && rightPiece.getMovesSinceFirstMove() == 1) {
                if (rightPiece.getColor() == PieceColor.BLACK)
                    return (rightPiece.getYCoord() == 3);
                else
                    return (rightPiece.getYCoord() == 4);
            }
        return false;
    }
}
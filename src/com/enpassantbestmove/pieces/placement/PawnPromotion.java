package com.enpassantbestmove.pieces.placement;

import com.enpassantbestmove.Main;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

public class PawnPromotion {

    public static boolean validate(Piece previousSelection) {
        if (previousSelection.getType() == PieceType.PAWN && previousSelection.getColor() == PieceColor.BLACK)
            return previousSelection.getYCoord() == 7;
        else if (previousSelection.getType() == PieceType.PAWN && previousSelection.getColor() == PieceColor.WHITE)
            return previousSelection.getYCoord() == 0;
        return false;
    }

    public static void execute(Piece previousSelection) {
        if (previousSelection.getType() == PieceType.PAWN && previousSelection.getColor() == PieceColor.BLACK) {
            if (previousSelection.getYCoord() == 7)
                Main.getCurrentScreen().setPawnPromotionScreen(previousSelection.getColor(), previousSelection.getXCoord(), previousSelection.getYCoord());
        } else if (previousSelection.getType() == PieceType.PAWN && previousSelection.getColor() == PieceColor.WHITE) {
            if (previousSelection.getYCoord() == 0)
                Main.getCurrentScreen().setPawnPromotionScreen(previousSelection.getColor(), previousSelection.getXCoord(), previousSelection.getYCoord());
        }
    }
}

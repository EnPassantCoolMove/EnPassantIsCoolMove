package com.enpassantbestmove.gamerules;

import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedFuture;
import com.enpassantbestmove.movementvalidation.movementblockers.NoEnPassantAvailable;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;

public class EnPassantCheckmateDetection {

    public static boolean black(Piece previousSelection) {
        if (previousSelection.getColor() == PieceColor.WHITE)
            return !NoEnPassantAvailable.validate(PieceColor.BLACK)
                    && !KingNotCheckedFuture.validate(NoEnPassantAvailable.afterEnpassant(PieceColor.BLACK), PieceColor.BLACK);
        return false;
    }

    public static boolean white(Piece previousSelection) {
        if (previousSelection.getColor() == PieceColor.BLACK)
            return !NoEnPassantAvailable.validate(PieceColor.WHITE)
                    && !KingNotCheckedFuture.validate(NoEnPassantAvailable.afterEnpassant(PieceColor.WHITE), PieceColor.WHITE);
        return false;
    }
}

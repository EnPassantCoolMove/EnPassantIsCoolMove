package com.enpassantbestmove.movementvalidation.movementblockers;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

import java.util.Arrays;
import java.util.Objects;

// checks if king is checked in the current board position

public class KingNotCheckedCurrently {

    public static boolean validate(PieceColor colorDefending) {
        PieceColor colorAttacking;
        if (colorDefending == PieceColor.BLACK)
            colorAttacking = PieceColor.WHITE;
        else
            colorAttacking = PieceColor.BLACK;

        return Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getType() == PieceType.KING)
                .filter(king -> king.getColor() == colorDefending)
                .allMatch(king ->
                        PieceNotAttacked.validate(BoardFactory.createPieceLayoutCopy(), colorAttacking, king.getXCoord(), king.getYCoord()));
    }
}

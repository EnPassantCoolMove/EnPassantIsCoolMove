package com.enpassantbestmove.movementvalidation.movementblockers;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

import java.util.Arrays;
import java.util.Objects;

// checks if king is checked in a future board position

public class KingNotCheckedFuture {

    public static boolean validate(Piece previousSelection, int xCoord, int yCoord) {
        var futureBoardPieces = BoardFactory.createPieceLayoutCopy();
        futureBoardPieces[previousSelection.getXCoord()][previousSelection.getYCoord()] = null;
        var futureSelected = previousSelection.getCopy();
        futureSelected.setXCoord(xCoord);
        futureSelected.setYCoord(yCoord);
        futureBoardPieces[xCoord][yCoord] = futureSelected;

        var colorDefending = previousSelection.getColor();
        PieceColor colorAttacking;
        if (colorDefending == PieceColor.BLACK)
            colorAttacking = PieceColor.WHITE;
        else
            colorAttacking = PieceColor.BLACK;

        return Arrays.stream(futureBoardPieces)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getType() == PieceType.KING)
                .filter(king -> king.getColor() == colorDefending)
                .allMatch(king ->
                        PieceNotAttacked.validate(futureBoardPieces, colorAttacking, king.getXCoord(), king.getYCoord()));
    }

    public static boolean validate(Piece[][] pieceLayout, PieceColor colorDefending) {
        var futureBoardPieces = pieceLayout;

        PieceColor colorAttacking;
        if (colorDefending == PieceColor.BLACK)
            colorAttacking = PieceColor.WHITE;
        else
            colorAttacking = PieceColor.BLACK;

        return Arrays.stream(futureBoardPieces)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getType() == PieceType.KING)
                .filter(king -> king.getColor() == colorDefending)
                .allMatch(king ->
                        PieceNotAttacked.validate(futureBoardPieces, colorAttacking, king.getXCoord(), king.getYCoord()));
    }
}

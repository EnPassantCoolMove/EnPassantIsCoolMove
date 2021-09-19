package com.enpassantbestmove.movementvalidation.movementblockers;

import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;

import java.util.Arrays;
import java.util.Objects;

// checks if piece is currently attacked

public class PieceNotAttacked {

    public static boolean validate(Piece[][] pieceLayout, PieceColor colorAttacking, int xCoord, int yCoord) {
        var attackedTile = pieceLayout[xCoord][yCoord];
        return Arrays.stream(pieceLayout)
                        .flatMap(Arrays::stream)
                        .filter(Objects::nonNull)
                        .filter(piece -> piece.getColor() == colorAttacking)
                        .noneMatch(enemyPiece ->
                                enemyPiece.canTake(pieceLayout, attackedTile.getXCoord(), attackedTile.getYCoord()));
    }
}

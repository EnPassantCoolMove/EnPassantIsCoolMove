package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.pieces.Piece;

import java.util.Arrays;
import java.util.Objects;


// disallows collisions between pieces

public class CollisionValidation {

    public static boolean validate(Piece[][] pieceLayout, Piece previousSelection, int xCoord, int yCoord) {
        var xCoord1 = xCoord;
        var yCoord1 = yCoord;
        var xCoord2 = previousSelection.getXCoord();
        var yCoord2 = previousSelection.getYCoord();

        return Arrays.stream(pieceLayout)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getXCoord() != xCoord1 || piece.getYCoord() != yCoord1)
                .filter(piece -> piece.getXCoord() != xCoord2 || piece.getYCoord() != yCoord2)
                .allMatch(piece -> Math.abs(Math.sqrt(Math.pow((xCoord1 - xCoord2), 2)
                            + Math.pow((yCoord1 - yCoord2), 2))
                            - (Math.sqrt(Math.pow((xCoord1 - piece.getXCoord()), 2)
                            + Math.pow((yCoord1 - piece.getYCoord()), 2))
                            + Math.sqrt(Math.pow((xCoord2 - piece.getXCoord()), 2)
                            + Math.pow((yCoord2 - piece.getYCoord()), 2)))) >= 0.01);
    }
}

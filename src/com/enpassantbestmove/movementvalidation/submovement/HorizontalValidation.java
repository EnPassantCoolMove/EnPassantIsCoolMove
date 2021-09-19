package com.enpassantbestmove.movementvalidation.submovement;

import com.enpassantbestmove.pieces.Piece;

// allows horizontal movement

public class HorizontalValidation {

    public static boolean validate(Piece previousSelection, int yCoord) {
        return previousSelection.getYCoord() == yCoord;
    }
}

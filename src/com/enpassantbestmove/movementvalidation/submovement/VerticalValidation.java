package com.enpassantbestmove.movementvalidation.submovement;

import com.enpassantbestmove.pieces.Piece;

// allows vertical movement

public class VerticalValidation {

    public static boolean validate(Piece previousSelection, int xCoord) {
        return previousSelection.getXCoord() == xCoord;
    }
}

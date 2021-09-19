package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.movementvalidation.submovement.HorizontalValidation;
import com.enpassantbestmove.movementvalidation.submovement.VerticalValidation;
import com.enpassantbestmove.pieces.Piece;

// allows straight movement

public class StraightValidation {

    public static boolean validate(Piece previousSelection, int xCoord, int yCoord) {
        return VerticalValidation.validate(previousSelection, xCoord) || HorizontalValidation.validate(previousSelection, yCoord);
    }
}

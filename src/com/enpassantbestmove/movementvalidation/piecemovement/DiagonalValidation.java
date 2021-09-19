package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.movementvalidation.submovement.SlopeValidation;
import com.enpassantbestmove.pieces.Piece;

import java.util.Set;

// allows diagonal movement

public class DiagonalValidation {

    public static boolean validate(Piece previousSelection, int xCoord, int yCoord) {
        return SlopeValidation.validateMultiple(Set.of((float)1, (float)-1), previousSelection, xCoord, yCoord);
    }
}

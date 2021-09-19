package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.movementvalidation.submovement.SlopeValidation;
import com.enpassantbestmove.pieces.Piece;

import java.util.Set;

// allows L shaped movement

public class LShapeValidation {

    public static boolean validate(Piece previousSelection, int xCoord, int yCoord) {
        return SlopeValidation.validateMultiple(Set.of((float)0.5, (float)-0.5, (float)2, (float)-2), previousSelection, xCoord, yCoord);
    }
}

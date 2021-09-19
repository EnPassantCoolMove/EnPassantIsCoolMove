package com.enpassantbestmove.movementvalidation.submovement;

import com.enpassantbestmove.pieces.Piece;

import java.util.Set;

// allows movement which follows a given slope

public class SlopeValidation {

    public static boolean validateOne(float slope, Piece previousSelection, int xCoord, int yCoord) {
        float xCoord1 = xCoord;
        float yCoord1 = yCoord;
        float xCoord2 = previousSelection.getXCoord();
        float yCoord2 = previousSelection.getYCoord();

        return (yCoord2 - yCoord1) / (xCoord2 - xCoord1) == slope;
    }

    public static boolean validateMultiple(Set<Float> slopes, Piece previousSelection, int xCoord, int yCoord) {
        float xCoord1 = xCoord;
        float yCoord1 = yCoord;
        float xCoord2 = previousSelection.getXCoord();
        float yCoord2 = previousSelection.getYCoord();

        return slopes.stream().anyMatch(slope -> (yCoord2 - yCoord1) / (xCoord2 - xCoord1) == slope);
    }
}

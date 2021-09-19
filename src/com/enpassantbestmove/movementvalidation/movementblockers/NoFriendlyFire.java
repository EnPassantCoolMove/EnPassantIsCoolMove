package com.enpassantbestmove.movementvalidation.movementblockers;

import com.enpassantbestmove.pieces.Piece;

// ensures no friendly fire between pieces

public class NoFriendlyFire {

    public static boolean validate(Piece[][] pieceLayout, Piece previousSelection, int xCoord, int yCoord) {
        if (pieceLayout[xCoord][yCoord] != null)
            return previousSelection.getColor() != pieceLayout[xCoord][yCoord].getColor();
        return true;
    }
}

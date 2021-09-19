package com.enpassantbestmove.movementvalidation.movementblockers;

import com.enpassantbestmove.pieces.Piece;

// returns info regarding what piece is on a tile

public class PieceOnTile {

    // returns if there is a piece on the tile
    public static boolean validate(Piece[][] pieceLayout, int xCoord, int yCoord) {
        return pieceLayout[xCoord][yCoord] != null;
    }

    // returns what piece is on the tile
    public static Piece getPiece(Piece[][] pieceLayout, int xCoord, int yCoord) {
        return pieceLayout[xCoord][yCoord];
    }
}

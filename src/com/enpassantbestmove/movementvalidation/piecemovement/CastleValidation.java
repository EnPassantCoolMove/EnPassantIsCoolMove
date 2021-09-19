package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedCurrently;
import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedFuture;
import com.enpassantbestmove.movementvalidation.movementblockers.PieceOnTile;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

// returns info regarding castling

public class CastleValidation {

    //returns how a king can castle
    public static CastleDirection whichWay(Piece previousSelection, int xCoord, int yCoord) {
        var blackKingLocation = PieceOnTile.getPiece(BoardFactory.createPieceLayoutCopy(), 4, 0);
        var blackRookQueensideLocation = PieceOnTile.getPiece(BoardFactory.createPieceLayoutCopy(), 0, 0);
        var blackRookKingsideLocation = PieceOnTile.getPiece(BoardFactory.createPieceLayoutCopy(), 7, 0);
        var whiteKingLocation = PieceOnTile.getPiece(BoardFactory.createPieceLayoutCopy(), 4, 7);
        var whiteRookQueensideLocation = PieceOnTile.getPiece(BoardFactory.createPieceLayoutCopy(), 0, 7);
        var whiteRookKingsideLocation = PieceOnTile.getPiece(BoardFactory.createPieceLayoutCopy(), 7, 7);

        if (KingNotCheckedCurrently.validate(previousSelection.getColor())) {
            if (previousSelection.getColor() == PieceColor.BLACK && blackKingLocation != null) {
                if (blackKingLocation.getMoves() == 0) {
                    if (xCoord == 2 && yCoord == 0) {
                        if (blackRookQueensideLocation != null)
                            if (blackKingLocation.getType() == PieceType.KING && blackKingLocation.getColor() == PieceColor.BLACK && blackKingLocation.getMoves() == 0)
                                if(!PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 1, 0)
                                    && !PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 2, 0)
                                    && !PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 3, 0)
                                    && blackRookQueensideLocation.getMoves() == 0
                                    && KingNotCheckedFuture.validate(blackKingLocation, 2, 0)
                                    && KingNotCheckedFuture.validate(blackKingLocation, 3, 0))
                                    return CastleDirection.QUEENSIDE;
                    } else if (xCoord == 6 && yCoord == 0) {
                        if (blackRookKingsideLocation != null)
                            if(!PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 5, 0)
                                    && !PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 6, 0)
                                    && blackRookKingsideLocation.getMoves() == 0
                                    && KingNotCheckedFuture.validate(blackKingLocation, 5, 0)
                                    && KingNotCheckedFuture.validate(blackKingLocation, 6, 0))
                                return CastleDirection.KINGSIDE;
                    }
                }
            } else if (previousSelection.getColor() == PieceColor.WHITE && whiteKingLocation != null) {
                if (whiteKingLocation.getMoves() == 0) {
                    if (xCoord == 2 && yCoord == 7) {
                        if (whiteRookQueensideLocation != null)
                            if(!PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 1, 7)
                                    && !PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 2, 7)
                                    && !PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 3, 7)
                                    && whiteRookQueensideLocation.getMoves() == 0
                                    && KingNotCheckedFuture.validate(whiteKingLocation, 2, 7)
                                    && KingNotCheckedFuture.validate(whiteKingLocation, 3, 7))
                                return CastleDirection.QUEENSIDE;
                    } else if (xCoord == 6 && yCoord == 7) {
                        if(whiteRookKingsideLocation != null)
                            if(!PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 5, 7)
                                    && !PieceOnTile.validate(BoardFactory.createPieceLayoutCopy(), 6, 7)
                                    && whiteRookKingsideLocation.getMoves() == 0
                                    && KingNotCheckedFuture.validate(whiteKingLocation, 5, 7)
                                    && KingNotCheckedFuture.validate(whiteKingLocation, 6, 7))
                                return CastleDirection.KINGSIDE;
                    }
                }
            }
        }
        return CastleDirection.ILLEGAL_MOVE;
    }

    // returns if a piece can castle
    public static boolean validate(Piece previousSelection, int xCoord, int yCoord) {
        return whichWay(previousSelection, xCoord, yCoord) != CastleDirection.ILLEGAL_MOVE;
    }
}

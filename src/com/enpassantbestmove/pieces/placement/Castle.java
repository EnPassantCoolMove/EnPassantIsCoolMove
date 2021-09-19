package com.enpassantbestmove.pieces.placement;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.movementvalidation.piecemovement.CastleValidation;
import com.enpassantbestmove.pieces.Piece;

// moves rook during a castle

public class Castle {

    public static void castle(Piece previousSelection, int xCoord, int yCoord) {
        var whichWay = CastleValidation.whichWay(previousSelection, xCoord, yCoord);

        switch (whichWay) {
            case KINGSIDE:
                RelocatePiece.relocate(BoardFactory.createPieceLayoutCopy()[xCoord + 1][yCoord], xCoord - 1, yCoord);
                break;

            case QUEENSIDE:
                RelocatePiece.relocate(BoardFactory.createPieceLayoutCopy()[xCoord - 2][yCoord], xCoord + 1, yCoord);
        }
    }
}

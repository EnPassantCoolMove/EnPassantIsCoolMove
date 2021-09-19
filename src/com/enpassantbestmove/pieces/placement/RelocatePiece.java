package com.enpassantbestmove.pieces.placement;

import com.enpassantbestmove.gui.board.BoardTile;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.pieces.Piece;

import java.util.Arrays;
import java.util.Objects;

// relocates piece

public class RelocatePiece {

    public static void relocate(Piece previousSelection, int xCoord, int yCoord) {
        // updates piece coords as well as the board
        BoardFactory.getBoard()[previousSelection.getXCoord()][previousSelection.getYCoord()].setPiece(null);
        BoardFactory.getBoard()[xCoord][yCoord].setPiece(previousSelection);
        BoardFactory.getBoard()[previousSelection.getXCoord()][previousSelection.getYCoord()].repaint();
        previousSelection.setXCoord(xCoord);
        previousSelection.setYCoord(yCoord);
        previousSelection.incrementMoves();

        // increments every piece's movesSinceFirstMove if the have already moved
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .map(BoardTile::getPiece)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getMoves() > 0)
                .forEach(Piece::incrementMovesSinceFirstMove);
    }
}

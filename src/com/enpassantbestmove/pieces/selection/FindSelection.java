package com.enpassantbestmove.pieces.selection;

import com.enpassantbestmove.gui.board.BoardTile;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.pieces.Piece;

import java.util.Arrays;

public class FindSelection {

    // returns the piece which is being pressed
    public Piece getSelectedPiece(int xCoord, int yCoord) {
        return BoardFactory.createPieceLayoutCopy()[xCoord][yCoord];
    }

    // returns the piece which is selected
    public Piece getPreviousSelection() {
        return Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(BoardTile::isSelectionIndicatorOn)
                .map(BoardTile::getPiece)
                .findAny()
                .orElse(null);
    }
}

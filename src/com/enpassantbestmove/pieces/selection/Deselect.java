package com.enpassantbestmove.pieces.selection;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.board.BoardTile;
import com.enpassantbestmove.pieces.Piece;

import java.util.Arrays;

public class Deselect {

    // deselects previous selected piece
    public static void deselectOne(Piece previousSelection) {
        BoardFactory.getBoard()[previousSelection.getXCoord()][previousSelection.getYCoord()].setSelectionIndicator(false);
        BoardFactory.getBoard()[previousSelection.getXCoord()][previousSelection.getYCoord()].repaint();
        Indicators.removeIndicators();
    }

    public static void deselectAll() {
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(BoardTile::isSelectionIndicatorOn)
                .forEach(tile -> {
                    tile.setSelectionIndicator(false);
                    tile.repaint();
                });
        Indicators.removeIndicators();
    }
}

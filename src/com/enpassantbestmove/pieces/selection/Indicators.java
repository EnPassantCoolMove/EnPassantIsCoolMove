package com.enpassantbestmove.pieces.selection;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.board.BoardTile;
import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedCurrently;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

import java.util.Arrays;
import java.util.Objects;

// places and removes tile overlays

public class Indicators {

    // places indicators except check indicators
    public static void placeIndicators(Piece currentSelection) {
        BoardFactory.getBoard()[currentSelection.getXCoord()][currentSelection.getYCoord()].setSelectionIndicator(true);

        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(tile -> {
                    if (tile.getPiece() == null)
                        return currentSelection.canMove(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord())
                                || currentSelection.canEnpassant(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord())
                                || currentSelection.canCastle(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord());
                    else
                        return currentSelection.canTake(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord());
                })
                .forEach(tile -> {
                    if (BoardFactory.getBoard()[tile.getXCoord()][tile.getYCoord()].getPiece() != null) {
                            tile.setAttackIndicator(true);
                    } else {
                        if (currentSelection.canEnpassant(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord()))
                            tile.setEnPassantIndicator(true);
                        else
                            tile.setMovementIndicator(true);
                    }
                    tile.repaint();
                });
    }

    // removes indicators except check indicator
    public static void removeIndicators() {
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(tile -> tile.isSelectionIndicatorOn() || tile.isMovementIndicatorOn() || tile.isAttackIndicatorOn() || tile.isEnPassantIndicatorOn())
                .forEach(tile -> {
                    tile.setSelectionIndicator(false);
                    tile.setMovementIndicator(false);
                    tile.setAttackIndicator(false);
                    tile.setEnPassantIndicator(false);
                    tile.repaint();
                });
    }

    // places check indicators
    public static void placeCheckIndicators(Piece previousSelection) {
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(tile -> tile.getPiece() != null)
                .filter(tile -> tile.getPiece().getType() == PieceType.KING)
                .filter(tile -> previousSelection.canTake(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord()))
                .forEach(tile -> {
                    tile.setCheckIndicator(true);
                    tile.repaint();
                });
    }

    // removes checkIndicators if conditions are met
    public static void removeCheckIndicators() {
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(BoardTile::isCheckIndicatorOn)
                .forEach(tile -> {
                    if (tile.getPiece() == null) {
                        tile.setCheckIndicator(false);
                        tile.repaint();
                    } else if (tile.getPiece().getColor() == PieceColor.BLACK) {
                        if (KingNotCheckedCurrently.validate(PieceColor.BLACK)) {
                            tile.setCheckIndicator(false);
                            tile.repaint();
                        }
                    } else {
                        if (KingNotCheckedCurrently.validate(PieceColor.WHITE)) {
                            tile.setCheckIndicator(false);
                            tile.repaint();
                        }
                    }
                });
    }

    // removes all indicators no matter what
    public static void forceRemoveAllIndicators() {
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(tile -> tile.isSelectionIndicatorOn() || tile.isMovementIndicatorOn() || tile.isAttackIndicatorOn() || tile.isEnPassantIndicatorOn() || tile.isCheckIndicatorOn())
                .forEach(tile -> {
                    tile.setSelectionIndicator(false);
                    tile.setMovementIndicator(false);
                    tile.setAttackIndicator(false);
                    tile.setEnPassantIndicator(false);
                    tile.setCheckIndicator(false);
                    tile.repaint();
                });
    }
}

package com.enpassantbestmove.gamerules;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedCurrently;
import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedFuture;
import com.enpassantbestmove.movementvalidation.movementblockers.NoEnPassantAvailable;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;

import java.util.Arrays;
import java.util.Objects;

// detects checkmate

public class RegularCheckmateDetection {

    public static boolean black(Piece previousSelection) {
        if (previousSelection.getColor() == PieceColor.WHITE)
            return !KingNotCheckedCurrently.validate(PieceColor.BLACK)
                    && Arrays.stream(BoardFactory.createPieceLayoutCopy())
                    .flatMap(Arrays::stream)
                    .filter(Objects::nonNull)
                    .filter(piece -> piece.getColor() == PieceColor.BLACK)
                    .allMatch(blackPiece ->
                            Arrays.stream(BoardFactory.getBoard())
                                    .flatMap(Arrays::stream)
                                    .noneMatch(tile -> {
                                        if (tile.getPiece() == null)
                                            return blackPiece.canMove(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord());
                                        else
                                            return blackPiece.canTake(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord());
                                    }))
                    && NoEnPassantAvailable.validate(PieceColor.BLACK);
        return false;
    }

    public static boolean white(Piece previousSelection) {
        if (previousSelection.getColor() == PieceColor.BLACK)
            return !KingNotCheckedCurrently.validate(PieceColor.WHITE)
                    && Arrays.stream(BoardFactory.createPieceLayoutCopy())
                    .flatMap(Arrays::stream)
                    .filter(Objects::nonNull)
                    .filter(piece -> piece.getColor() == PieceColor.WHITE)
                    .allMatch(whitePiece ->
                            Arrays.stream(BoardFactory.getBoard())
                                    .flatMap(Arrays::stream)
                                    .noneMatch(tile -> {
                                        if (tile.getPiece() == null)
                                            return whitePiece.canMove(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord());
                                        else
                                            return whitePiece.canTake(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord());
                                    }))
                    && NoEnPassantAvailable.validate(PieceColor.WHITE);
        return false;
    }
}


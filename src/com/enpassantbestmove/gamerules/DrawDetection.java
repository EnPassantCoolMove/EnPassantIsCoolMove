package com.enpassantbestmove.gamerules;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.movementvalidation.movementblockers.KingNotCheckedCurrently;
import com.enpassantbestmove.movementvalidation.movementblockers.NoEnPassantAvailable;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// detects draws

public class DrawDetection {

    public static boolean detectStalemate(Piece previousSelection) {
        if (previousSelection.getColor() == PieceColor.WHITE)
            return KingNotCheckedCurrently.validate(PieceColor.BLACK)
                    && NoEnPassantAvailable.validate(PieceColor.BLACK)
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
                                    }));
        else
            return KingNotCheckedCurrently.validate(PieceColor.WHITE)
                    && NoEnPassantAvailable.validate(PieceColor.WHITE)
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
                                    }));
    }

    public static boolean detectThreeFoldRepetition(List<String[][]> pieceIconLayoutList) {
        return pieceIconLayoutList.stream()
                .anyMatch(layout -> pieceIconLayoutList.stream()
                        .filter(secondLayout -> secondLayout != layout)
                        .filter(secondLayout -> Arrays.deepEquals(layout, secondLayout))
                        .anyMatch(secondLayout -> pieceIconLayoutList.stream()
                                .filter(thirdLayout -> thirdLayout != layout && thirdLayout != secondLayout)
                                .anyMatch(thirdLayout -> Arrays.deepEquals(layout, thirdLayout))));
    }

    public static boolean detectFiftyMoveRule() {
        return AdvantageCalculator.getMovesSinceLastUpdate() == 100;
    }

    public static boolean detectInsufficientMatingMaterial() {
        var hasNoRooksOrQueensOrPawns = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .map(Piece::getType)
                .noneMatch(pieceType -> pieceType == PieceType.ROOK || pieceType == PieceType.QUEEN || pieceType == PieceType.PAWN);

        var blackLightTileBishopsNumber = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.BLACK)
                .filter(piece -> piece.getType() == PieceType.BISHOP)
                .filter(piece -> (piece.getXCoord() + piece.getYCoord()) % 2 == 0)
                .count();

        var blackDarkTileBishopsNumber = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.BLACK)
                .filter(piece -> piece.getType() == PieceType.BISHOP)
                .filter(piece -> (piece.getXCoord() + piece.getYCoord()) % 2 == 1)
                .count();

        var whiteLightTileBishopsNumber = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.WHITE)
                .filter(piece -> piece.getType() == PieceType.BISHOP)
                .filter(piece -> (piece.getXCoord() + piece.getYCoord()) % 2 == 0)
                .count();

        var whiteDarkTileBishopsNumber = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.WHITE)
                .filter(piece -> piece.getType() == PieceType.BISHOP)
                .filter(piece -> (piece.getXCoord() + piece.getYCoord()) % 2 == 1)
                .count();

        var blackKnightsNumber = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.BLACK)
                .filter(piece -> piece.getType() == PieceType.KNIGHT)
                .count();

        var whiteKnightsNumber = Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.WHITE)
                .filter(piece -> piece.getType() == PieceType.KNIGHT)
                .count();

        return hasNoRooksOrQueensOrPawns
                && (((whiteDarkTileBishopsNumber == 0 || blackLightTileBishopsNumber == 0)
                        && (whiteLightTileBishopsNumber == 0 || blackDarkTileBishopsNumber == 0)
                        && (whiteDarkTileBishopsNumber == 0 || whiteLightTileBishopsNumber == 0)
                        && (blackDarkTileBishopsNumber == 0 || blackLightTileBishopsNumber == 0))
                    && whiteKnightsNumber == 0 && blackKnightsNumber == 0)
                || ((whiteDarkTileBishopsNumber + whiteLightTileBishopsNumber + blackDarkTileBishopsNumber + blackLightTileBishopsNumber) == 0
                    && whiteKnightsNumber <= 2 && blackKnightsNumber <= 2);
    }
}

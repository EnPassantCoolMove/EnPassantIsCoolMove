package com.enpassantbestmove.movementvalidation.movementblockers;

import com.enpassantbestmove.gui.board.BoardTile;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;
import com.enpassantbestmove.pieces.placement.KillByEnPassant;

import java.util.Arrays;
import java.util.Objects;

// checks entire board for possible en passants

public class NoEnPassantAvailable {
    private static Piece pieceUnder;
    private static Piece pieceAbove;

    // checks if en passant is possible
    public static boolean validate(PieceColor previousColor) {
        return enPassantingPiece(previousColor) == null;
    }

    // returns en passanting piece
    public static Piece enPassantingPiece(PieceColor previousColor) {
        PieceColor enPassantedColor;
        if (previousColor == PieceColor.WHITE)
            enPassantedColor = PieceColor.BLACK;
        else
            enPassantedColor = PieceColor.WHITE;

        return Arrays.stream(BoardFactory.createPieceLayoutCopy())
                        .flatMap(Arrays::stream)
                        .filter(Objects::nonNull)
                        .filter(piece -> piece.getType() == PieceType.PAWN)
                        .filter(piece -> piece.getColor() == previousColor)
                        .filter(pawn -> Arrays.stream(BoardFactory.getBoard())
                                .flatMap(Arrays::stream)
                                .filter(tile -> tile.getPiece() == null)
                                .filter(tile -> {
                                    if (enPassantedColor == PieceColor.BLACK) {
                                        try {
                                            pieceUnder = BoardFactory.getBoard()[tile.getXCoord()][tile.getYCoord() + 1].getPiece();
                                        } catch (Exception ignored) {}
                                        if (pieceUnder != null)
                                            return pieceUnder.getType() == PieceType.PAWN && pieceUnder.getColor() == enPassantedColor;
                                        else return false;
                                    } else {
                                        try {
                                            pieceAbove = BoardFactory.getBoard()[tile.getXCoord()][tile.getYCoord() - 1].getPiece();
                                        } catch (Exception ignored) {}
                                        if (pieceAbove != null)
                                            return pieceAbove.getType() == PieceType.PAWN && pieceAbove.getColor() == enPassantedColor;
                                        else return false;
                                    }
                                })
                                .anyMatch(tile -> pawn.canEnpassant(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord())))
                        .findAny()
                        .orElse(null);
    }

    // returns tile which en passanting piece will land on
    public static BoardTile endingTile(PieceColor previousColor) {
        PieceColor enPassantedColor;
        if (previousColor == PieceColor.WHITE)
            enPassantedColor = PieceColor.BLACK;
        else
            enPassantedColor = PieceColor.WHITE;

        return Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .filter(tile -> tile.getPiece() == null)
                .filter(tile -> {
                    if (enPassantedColor == PieceColor.BLACK) {
                        try {
                            pieceUnder = BoardFactory.getBoard()[tile.getXCoord()][tile.getYCoord() + 1].getPiece();
                        } catch (Exception ignored) {}
                        if (pieceUnder != null)
                            return pieceUnder.getType() == PieceType.PAWN && pieceUnder.getColor() == enPassantedColor;
                        else return false;
                    } else {
                        try {
                            pieceAbove = BoardFactory.getBoard()[tile.getXCoord()][tile.getYCoord() - 1].getPiece();
                        } catch (Exception ignored) {}
                        if (pieceAbove != null)
                            return pieceAbove.getType() == PieceType.PAWN && pieceAbove.getColor() == enPassantedColor;
                        else return false;
                    }
                })
                .filter(tile ->
                        Arrays.stream(BoardFactory.createPieceLayoutCopy())
                                .flatMap(Arrays::stream)
                                .filter(Objects::nonNull)
                                .filter(piece -> piece.getType() == PieceType.PAWN)
                                .filter(piece -> piece.getColor() == previousColor)
                                .anyMatch(pawn -> pawn.canEnpassant(BoardFactory.createPieceLayoutCopy(), tile.getXCoord(), tile.getYCoord())))
                .findAny()
                .orElse(null);
    }

    // returns board layout after en passant is completed
    public static Piece[][] afterEnpassant(PieceColor previousColor) {
        var futureBoardPieces = BoardFactory.createPieceLayoutCopy();
        futureBoardPieces[enPassantingPiece(previousColor).getXCoord()][enPassantingPiece(previousColor).getYCoord()] = null;
        var futureEnpassantingPiece = enPassantingPiece(previousColor).getCopy();
        futureEnpassantingPiece.setXCoord(endingTile(previousColor).getXCoord());
        futureEnpassantingPiece.setYCoord(endingTile(previousColor).getYCoord());
        futureBoardPieces[endingTile(previousColor).getXCoord()][endingTile(previousColor).getYCoord()] = futureEnpassantingPiece;
        KillByEnPassant.kill(futureBoardPieces, endingTile(previousColor).getXCoord(), endingTile(previousColor).getYCoord());

        return futureBoardPieces;
    }
}

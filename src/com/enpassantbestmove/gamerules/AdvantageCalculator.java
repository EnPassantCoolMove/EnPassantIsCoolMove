package com.enpassantbestmove.gamerules;

import com.enpassantbestmove.gui.GUIColors;
import com.enpassantbestmove.gui.sidebar.AdvantageDisplay;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.sidebar.AdvantageBar;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.PieceType;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class AdvantageCalculator {
    private static int movesSinceLastUpdate = 0;

    public static void updateStart(AdvantageDisplay displayLabel) {
        if (calculate() > 0) {
            displayLabel.setText("+" + calculate());
            displayLabel.setForeground(GUIColors.OFF_WHITE);
        } else if (calculate() < 0) {
            displayLabel.setText("+" + Math.abs(calculate()));
            displayLabel.setForeground(Color.BLACK);
        } else {
            displayLabel.setText(null);
        }
    }

    public static void update(AdvantageDisplay displayLabel, AdvantageBar displayBar, Piece currentSelection, Piece previousSelection) {
        if (currentSelection != null || previousSelection.getType() == PieceType.PAWN) {
            if (calculate() > 0) {
                displayLabel.setText("+" + calculate());
                displayLabel.setForeground(GUIColors.OFF_WHITE);
                displayBar.setMaximum(AdvantageCalculator.getTotalPieceValue());
                displayBar.setValue(AdvantageCalculator.getTotalWhitePieceValue());
            } else if (calculate() < 0) {
                displayLabel.setText("+" + Math.abs(calculate()));
                displayLabel.setForeground(Color.BLACK);
            } else
                displayLabel.setText(null);
            resetMovesSinceLastUpdate();
            displayBar.setMaximum(AdvantageCalculator.getTotalPieceValue());
            displayBar.setValue(AdvantageCalculator.getTotalWhitePieceValue());
            if (onlyKingsLeft()) {
                displayBar.setMaximum(2);
                displayBar.setValue(1);
            }
        }
        else
            incrementMovesSinceLastUpdate();
    }

    public static int calculate() {
        return getTotalWhitePieceValue() - getTotalBlackPieceValue();
    }

    public static int getTotalWhitePieceValue() {
        return Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.WHITE)
                .map(Piece::getPieceValue)
                .reduce(0, Integer::sum);
    }

    public static int getTotalBlackPieceValue() {
        return Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getColor() == PieceColor.BLACK)
                .map(Piece::getPieceValue)
                .reduce(0, Integer::sum);
    }

    public static int getTotalPieceValue() {
        return getTotalWhitePieceValue() + getTotalBlackPieceValue();
    }

    public static boolean onlyKingsLeft() {
        return Arrays.stream(BoardFactory.createPieceLayoutCopy())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .noneMatch(piece -> piece.getType() != PieceType.KING);
    }

    public static int getMovesSinceLastUpdate() {
        return movesSinceLastUpdate;
    }

    public static void incrementMovesSinceLastUpdate() {
        movesSinceLastUpdate++;
    }

    public static void resetMovesSinceLastUpdate() {
        movesSinceLastUpdate = 0;
    }
}

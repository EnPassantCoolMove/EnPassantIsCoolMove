package com.enpassantbestmove.movementvalidation.piecemovement;

import com.enpassantbestmove.movementvalidation.submovement.HorizontalValidation;
import com.enpassantbestmove.movementvalidation.submovement.VerticalValidation;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;

public class DistanceValidation {

    public static boolean validate(float distance, Piece previousSelection, int xCoord, int yCoord) {
        if (VerticalValidation.validate(previousSelection, xCoord))
            return Math.abs(yCoord - previousSelection.getYCoord()) <= distance;
        else if (HorizontalValidation.validate(previousSelection, yCoord))
            return Math.abs(xCoord - previousSelection.getXCoord()) <= distance;
        else {
            return (float)Math.sqrt(Math.pow(
                    (previousSelection.getXCoord() - xCoord), 2)
                    + Math.pow((previousSelection.getYCoord() - yCoord), 2)) <= distance;
        }
    }

    public static boolean validateFront(float front, Piece previousSelection, int yCoord) {
        if (previousSelection.getColor() == PieceColor.BLACK)
            return yCoord - previousSelection.getYCoord() <= front
                    && yCoord - previousSelection.getYCoord() > 0;
        else
            return previousSelection.getYCoord() - yCoord <= front
                    && previousSelection.getYCoord() - yCoord > 0;
    }

    public static boolean validateRight(float right, Piece previousSelection, int xCoord) {
        return xCoord - previousSelection.getXCoord() <= right
                && xCoord - previousSelection.getXCoord() > 0;
    }

    public static boolean validateLeft(float left, Piece previousSelection, int xCoord) {
        return previousSelection.getXCoord() - xCoord <= left
                && previousSelection.getXCoord() - xCoord > 0;
    }
}

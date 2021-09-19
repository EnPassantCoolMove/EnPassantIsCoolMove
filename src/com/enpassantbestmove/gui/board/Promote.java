package com.enpassantbestmove.gui.board;

import com.enpassantbestmove.Main;
import com.enpassantbestmove.pieces.*;

// replaces the promoting pawn then changes screen back to the game and then changes turn

public class Promote {

    public static void promote(PieceType type, PieceColor color, int xCoord, int yCoord) {
        if (type == PieceType.QUEEN)
            BoardFactory.getBoard()[xCoord][yCoord].setPiece(new Queen(color, xCoord, yCoord));
        else if (type == PieceType.ROOK)
            BoardFactory.getBoard()[xCoord][yCoord].setPiece(new Rook(color, xCoord, yCoord));
        else if (type == PieceType.KNIGHT)
            BoardFactory.getBoard()[xCoord][yCoord].setPiece(new Knight(color, xCoord, yCoord));
        else if (type == PieceType.BISHOP)
            BoardFactory.getBoard()[xCoord][yCoord].setPiece(new Bishop(color, xCoord, yCoord));

        Main.getCurrentScreen().returnToGameScreen(true);
        BoardFactory.changeTurn(BoardFactory.getBoard()[xCoord][yCoord].getPiece(), BoardFactory.getBoard()[xCoord][yCoord].getPiece());
    }
}

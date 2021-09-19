package com.enpassantbestmove.pieces.placement;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.pieces.Piece;

// kills piece underneath / above attacking pawn during en passant

public class KillByEnPassant {

    // used during theoretical board calculation
    public static void kill(Piece[][] pieceLayout, int xCoord, int yCoord) {
        if (yCoord == 2) {
            pieceLayout[xCoord][yCoord + 1] = null;
        } else if (yCoord == 5) {
            pieceLayout[xCoord][yCoord - 1] = null;
        }
    }

    // used to update current displayed board
    public static void killAndUpdate(int xCoord, int yCoord) {
        if (yCoord == 2) {
            BoardFactory.getBoard()[xCoord][yCoord + 1].setPiece(null);
            BoardFactory.getBoard()[xCoord][yCoord + 1].repaint();
        } else if (yCoord == 5) {
            BoardFactory.getBoard()[xCoord][yCoord - 1].setPiece(null);
            BoardFactory.getBoard()[xCoord][yCoord - 1].repaint();
        }
    }
}

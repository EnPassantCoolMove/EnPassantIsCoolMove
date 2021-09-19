package com.enpassantbestmove.pieces.layout;

import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;

// allows for the board to be decoupled from the layout

public interface Layout {
    Piece[][] getLayout();
    PieceColor getTurn();
}

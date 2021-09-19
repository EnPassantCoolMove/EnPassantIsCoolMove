package com.enpassantbestmove.pieces.layout;

import com.enpassantbestmove.pieces.*;

public class TestingLayout implements Layout{
    private Piece[][] pieces;
    private PieceColor turn;

    public TestingLayout() {
        turn = PieceColor.BLACK;
        pieces = new Piece[8][8];

        pieces[3][0] = new Queen(PieceColor.BLACK);
        pieces[4][0] = new King(PieceColor.BLACK);

        pieces[3][7] = new Queen(PieceColor.WHITE);
        pieces[4][7] = new King(PieceColor.WHITE);
    }

    @Override
    public Piece[][] getLayout() {
        return pieces;
    }

    @Override
    public PieceColor getTurn() {
        return turn;
    }
}

package com.enpassantbestmove.pieces.layout;

import com.enpassantbestmove.pieces.*;

public class TestingLayout3 implements Layout{
    private Piece[][] pieces;
    private PieceColor turn;

    public TestingLayout3() {
        turn = PieceColor.WHITE;
        pieces = new Piece[8][8];

        pieces[4][0] = new King(PieceColor.BLACK);
        pieces[0][1] = new Bishop(PieceColor.BLACK);
        pieces[1][1] = new Bishop(PieceColor.BLACK);
        pieces[2][1] = new Bishop(PieceColor.BLACK);

        pieces[4][7] = new King(PieceColor.WHITE);
        pieces[0][6] = new Bishop(PieceColor.WHITE);
        pieces[1][6] = new Bishop(PieceColor.WHITE);
        pieces[2][6] = new Bishop(PieceColor.WHITE);
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

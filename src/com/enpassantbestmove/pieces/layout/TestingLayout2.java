package com.enpassantbestmove.pieces.layout;

import com.enpassantbestmove.pieces.*;

public class TestingLayout2 implements Layout{
    private Piece[][] pieces;
    private PieceColor turn;

    public TestingLayout2() {
        turn = PieceColor.WHITE;
        pieces = new Piece[8][8];
        
        pieces[4][0] = new King(PieceColor.BLACK);
        pieces[0][1] = new Bishop(PieceColor.BLACK);
        pieces[1][1] = new Bishop(PieceColor.BLACK);
        pieces[2][1] = new Bishop(PieceColor.BLACK);
        pieces[3][1] = new Bishop(PieceColor.BLACK);
        pieces[4][1] = new Bishop(PieceColor.BLACK);
        pieces[5][1] = new Bishop(PieceColor.BLACK);
        pieces[6][1] = new Bishop(PieceColor.BLACK);
        pieces[7][1] = new Bishop(PieceColor.BLACK);

        pieces[4][7] = new King(PieceColor.WHITE);
        pieces[0][6] = new Bishop(PieceColor.WHITE);
        pieces[1][6] = new Bishop(PieceColor.WHITE);
        pieces[2][6] = new Bishop(PieceColor.WHITE);
        pieces[3][6] = new Bishop(PieceColor.WHITE);
        pieces[4][6] = new Bishop(PieceColor.WHITE);
        pieces[5][6] = new Bishop(PieceColor.WHITE);
        pieces[6][6] = new Bishop(PieceColor.WHITE);
        pieces[7][6] = new Bishop(PieceColor.WHITE);
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

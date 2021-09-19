package com.enpassantbestmove.pieces.layout;

import com.enpassantbestmove.pieces.*;

// creates the standard chess layout

public class StandardLayout implements Layout {
    private Piece[][] pieces;
    private PieceColor turn;

    public StandardLayout() {
        turn = PieceColor.WHITE;
        pieces = new Piece[8][8];
        
        pieces[0][0] = new Rook(PieceColor.BLACK);
        pieces[1][0] = new Knight(PieceColor.BLACK);
        pieces[2][0] = new Bishop(PieceColor.BLACK);
        pieces[3][0] = new Queen(PieceColor.BLACK);
        pieces[4][0] = new King(PieceColor.BLACK);
        pieces[5][0] = new Bishop(PieceColor.BLACK);
        pieces[6][0] = new Knight(PieceColor.BLACK);
        pieces[7][0] = new Rook(PieceColor.BLACK);
        pieces[0][1] = new Pawn(PieceColor.BLACK);
        pieces[1][1] = new Pawn(PieceColor.BLACK);
        pieces[2][1] = new Pawn(PieceColor.BLACK);
        pieces[3][1] = new Pawn(PieceColor.BLACK);
        pieces[4][1] = new Pawn(PieceColor.BLACK);
        pieces[5][1] = new Pawn(PieceColor.BLACK);
        pieces[6][1] = new Pawn(PieceColor.BLACK);
        pieces[7][1] = new Pawn(PieceColor.BLACK);

        pieces[0][7] = new Rook(PieceColor.WHITE);
        pieces[1][7] = new Knight(PieceColor.WHITE);
        pieces[2][7] = new Bishop(PieceColor.WHITE);
        pieces[3][7] = new Queen(PieceColor.WHITE);
        pieces[4][7] = new King(PieceColor.WHITE);
        pieces[5][7] = new Bishop(PieceColor.WHITE);
        pieces[6][7] = new Knight(PieceColor.WHITE);
        pieces[7][7] = new Rook(PieceColor.WHITE);
        pieces[0][6] = new Pawn(PieceColor.WHITE);
        pieces[1][6] = new Pawn(PieceColor.WHITE);
        pieces[2][6] = new Pawn(PieceColor.WHITE);
        pieces[3][6] = new Pawn(PieceColor.WHITE);
        pieces[4][6] = new Pawn(PieceColor.WHITE);
        pieces[5][6] = new Pawn(PieceColor.WHITE);
        pieces[6][6] = new Pawn(PieceColor.WHITE);
        pieces[7][6] = new Pawn(PieceColor.WHITE);
    }

    @Override
    public Piece[][] getLayout(){
        return pieces;
    }

    @Override
    public PieceColor getTurn() {
        return turn;
    }
}

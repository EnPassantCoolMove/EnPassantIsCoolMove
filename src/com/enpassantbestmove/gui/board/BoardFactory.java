package com.enpassantbestmove.gui.board;

import com.enpassantbestmove.Main;
import com.enpassantbestmove.gamerules.AdvantageCalculator;
import com.enpassantbestmove.gamerules.EndOfGame;
import com.enpassantbestmove.gui.sidebar.SideBarFactory;
import com.enpassantbestmove.pieces.PieceColor;
import com.enpassantbestmove.pieces.layout.Layout;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.placement.PawnPromotion;
import com.enpassantbestmove.pieces.selection.Indicators;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// creates a chess board

public class BoardFactory extends JPanel {
    private static BoardTile[][] boardTiles;
    private static Piece[][] pieceLayoutCopy;
    private static List<Piece[][]> pieceLayoutList;
    private static String[][] pieceIconLayoutCopy;
    private static List<String[][]> pieceIconLayoutList;

    private static PieceColor turn;
    private static boolean gameStarted = false;
    private static int rows;
    private static int columns;
    private static int xCoord;
    private static int yCoord;
    private Piece piece;

    public BoardFactory(int rows, int columns, Layout pieceLayout, ImageIcon selectionIndicator, ImageIcon movementIndicator, ImageIcon attackIndicator, ImageIcon enPassantIndicator, ImageIcon checkIndicator) {
        BoardFactory.rows = rows;
        BoardFactory.columns = columns;

        this.setLayout(new GridLayout(rows, columns));
        this.setBounds(0, 0, 480, 480);
        setOpaque(false);

        boardTiles = new BoardTile[rows][columns];
        pieceLayoutList = new ArrayList<>();
        pieceIconLayoutList = new ArrayList<>();
        turn = pieceLayout.getTurn();

        // sets up board to include all the pieces in the given piece layout
        for (yCoord = 0; yCoord < rows; yCoord++) {
            for (xCoord = 0; xCoord < columns; xCoord++) {
                piece = pieceLayout.getLayout()[xCoord][yCoord];
                if (piece != null) {
                    piece.setXCoord(xCoord);
                    piece.setYCoord(yCoord);
                }
                var currentTile = new BoardTile(xCoord, yCoord, piece, selectionIndicator, movementIndicator, attackIndicator, enPassantIndicator, checkIndicator);
                this.add(currentTile);
                boardTiles[xCoord][yCoord] = currentTile;
            }
        }

        // updates the pieceLayoutList and the pieceIconLayoutList to include the starting position
        pieceLayoutList.add(BoardFactory.createPieceLayoutCopy());
        pieceIconLayoutList.add(BoardFactory.createPieceIconLayoutCopy());
    }

    // used when interacting directly with tiles (ex: updating tile graphics)
    public static BoardTile[][] getBoard() {
        return boardTiles;
    }

    // creates a copy of the current piece layout (fuck you static)
    // used when interacting with pieces as it has more functionality due it allowing the creation of theoretical board positions without altering the current state of the displayed board
    public static Piece[][] createPieceLayoutCopy() {
        pieceLayoutCopy = new Piece[rows][columns];

        for (yCoord = 0; yCoord < rows; yCoord++) {
            for (xCoord = 0; xCoord < columns; xCoord++) {
                pieceLayoutCopy[xCoord][yCoord] = boardTiles[xCoord][yCoord].getPiece();
            }
        }

        return pieceLayoutCopy;
    }

    // creates a copy of the current piece icon layout
    // used when checking for board position as each piece which has the same icon will be represented the same
    public static String[][] createPieceIconLayoutCopy() {
        pieceIconLayoutCopy = new String[rows][columns];

        for (yCoord = 0; yCoord < rows; yCoord++) {
            for (xCoord = 0; xCoord < columns; xCoord++) {
                if (boardTiles[xCoord][yCoord].getPiece() != null)
                    pieceIconLayoutCopy[xCoord][yCoord] = boardTiles[xCoord][yCoord].getPiece().getPieceIcon().toString();
            }
        }

        return pieceIconLayoutCopy;
    }

    public static PieceColor getTurn() {
        return turn;
    }

    public static void changeTurn(Piece currentSelectedPiece, Piece previousSelectedPiece) {
        // checks if there is an available pawn promotion, if not: change turn, else: wait till the pawn promotion is accepted to change the turn
        if (!PawnPromotion.validate(previousSelectedPiece)) {
            if (!gameStarted) // if the game has not started, start the game
                gameStarted = true;

            // updates the pieceLayoutList and the pieceIconLayoutList every turn
            pieceLayoutList.add(BoardFactory.createPieceLayoutCopy());
            pieceIconLayoutList.add(BoardFactory.createPieceIconLayoutCopy());

            // places and removes check indicators
            Indicators.placeCheckIndicators(previousSelectedPiece);
            Indicators.removeCheckIndicators();

            // auto declines draws if it has been offered but one of the players move
            if (EndOfGame.isDrawOffered())
                EndOfGame.delcineDraw();

            AdvantageCalculator.update(SideBarFactory.getAdvantageDisplay(), SideBarFactory.getAdvantageBar(), currentSelectedPiece, previousSelectedPiece); // updates advantageLabel

            EndOfGame.detect(previousSelectedPiece); // checks for wins and draws

            // updates timers
            if (turn == PieceColor.WHITE) {
                SideBarFactory.getWhiteTimer().setMillisecondsAllowed(SideBarFactory.getWhiteTimer().getMillisecondsAllowed() + SideBarFactory.getWhiteTimer().getMillisecondsOfIncrement());
                SideBarFactory.getWhiteTimer().update();
                turn = PieceColor.BLACK;
            } else {
                SideBarFactory.getBlackTimer().setMillisecondsAllowed(SideBarFactory.getBlackTimer().getMillisecondsAllowed() + SideBarFactory.getBlackTimer().getMillisecondsOfIncrement());
                SideBarFactory.getBlackTimer().update();
                turn = PieceColor.WHITE;
            }
        } else {
            PawnPromotion.execute(previousSelectedPiece);
        }
    }

    public static List<Piece[][]> getPieceLayoutList() {
        return pieceLayoutList;
    }

    public static List<String[][]> getPieceIconLayoutList() {
        return pieceIconLayoutList;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void disableTiles() {
        Arrays.stream(BoardFactory.getBoard())
                .flatMap(Arrays::stream)
                .forEach(tile -> tile.setEnabled(false));
    }

    public static int getRows() {
        return rows;
    }

    public static int getColumns() {
        return columns;
    }

    public static void setGameStarted(boolean gameStarted) {
        BoardFactory.gameStarted = gameStarted;
    }
}

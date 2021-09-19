package com.enpassantbestmove.gamerules;

import com.enpassantbestmove.Main;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.sidebar.SideBarFactory;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.PieceColor;

import javax.swing.*;

public class EndOfGame {
    private static boolean drawOffered = false;

    public static void detect(Piece previousSelectedPiece) {
        if (RegularCheckmateDetection.black(previousSelectedPiece))
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/CheckmateRegularBlack.gif")), "White Won by Checkmate");
        else if (RegularCheckmateDetection.white(previousSelectedPiece))
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/CheckmateRegularWhite.gif")), "Black Won by Checkmate");
        else if (EnPassantCheckmateDetection.black(previousSelectedPiece))
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/CheckmateEnPassantBlack.gif")), "White Won by ENPASSANT");
        else if (EnPassantCheckmateDetection.white(previousSelectedPiece))
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/CheckmateEnPassantWhite.gif")), "Black Won by ENPASSANT");
        else if (DrawDetection.detectStalemate(previousSelectedPiece))
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/Stalemate.gif")), "Draw by Stalemate");
        else if (DrawDetection.detectFiftyMoveRule())
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/FiftyMoveRule.gif")), "Draw by 50 Move Rule");
        else if (DrawDetection.detectThreeFoldRepetition(BoardFactory.getPieceIconLayoutList()))
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/Repetition.gif")), "Draw by 3 Fold Repetition");
        else if (DrawDetection.detectInsufficientMatingMaterial())
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/InsufficientMaterial.gif")), "Draw by Insufficient Mating Material");
    }

    public static void timeout(PieceColor turn) {
        if (turn == PieceColor.BLACK)
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/TimeoutBlack.gif")), "White Won by Timeout");
        else
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/TimeoutWhite.gif")), "Black Won by Timeout");
    }

    public static void resignation(PieceColor turn) {
        if (turn == PieceColor.BLACK)
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/ResignBlack.gif")), "White Won by Resignation");
        else
            Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/ResignWhite.gif")), "Black Won by Resignation");
    }

    public static void offerDraw(PieceColor color) {
        drawOffered = true;
        SideBarFactory.getBlackDrawButton().setEnabled(false);
        SideBarFactory.getWhiteDrawButton().setEnabled(false);
        SideBarFactory.getAcceptDrawButton().setEnabled(true);
        SideBarFactory.getAcceptDrawButton().setVisible(true);
        SideBarFactory.getDeclineDrawButton().setEnabled(true);
        SideBarFactory.getDeclineDrawButton().setVisible(true);
        if (color == PieceColor.WHITE) {
            SideBarFactory.getWhiteDrawOfferLabel().setVisible(true);
            SideBarFactory.getWhiteDrawOfferLabel().getForegroundText().setVisible(true);
        } else {
            SideBarFactory.getBlackDrawOfferLabel().setVisible(true);
            SideBarFactory.getBlackDrawOfferLabel().getForegroundText().setVisible(true);
        }
    }

    public static void acceptDraw() {
        drawOffered = false;
        Main.getCurrentScreen().setEndScreen(new ImageIcon(EndOfGame.class.getClassLoader().getResource("animations/endscreen/Agreement.gif")), "Draw Accepted");
    }

    public static void delcineDraw() {
        drawOffered = false;
        SideBarFactory.getBlackDrawButton().setEnabled(true);
        SideBarFactory.getWhiteDrawButton().setEnabled(true);
        SideBarFactory.getAcceptDrawButton().setEnabled(false);
        SideBarFactory.getAcceptDrawButton().setVisible(false);
        SideBarFactory.getDeclineDrawButton().setEnabled(false);
        SideBarFactory.getDeclineDrawButton().setVisible(false);
        SideBarFactory.getWhiteDrawOfferLabel().setVisible(false);
        SideBarFactory.getWhiteDrawOfferLabel().getForegroundText().setVisible(false);
        SideBarFactory.getBlackDrawOfferLabel().setVisible(false);
        SideBarFactory.getBlackDrawOfferLabel().getForegroundText().setVisible(false);
    }

    public static boolean isDrawOffered() {
        return drawOffered;
    }
}

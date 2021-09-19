package com.enpassantbestmove.gui;

import com.enpassantbestmove.gui.board.Promote;
import com.enpassantbestmove.gui.functions.StartGame;
import com.enpassantbestmove.gui.general.*;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.sidebar.SideBarFactory;
import com.enpassantbestmove.pieces.*;
import com.enpassantbestmove.pieces.selection.Deselect;
import com.enpassantbestmove.pieces.selection.Indicators;

import javax.swing.*;

// implements all the gui elements together to create a gui

public class Screen extends JFrame {
    private BoardFactory currentChessBoardPanel;
    private SideBarFactory currentChessSidePanel;
    private JLabel currentGameScreen;

    public Screen() {

        setTitleScreen();
        this.setTitle("EPCM");
        this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("components/Icon.png")).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTitleScreen() {
        this.getContentPane().removeAll();

        this.add(new InvisibleButton(GUIImages.startButtonImage, GUIImages.startButtonPressedImage, 25, 175, e -> {
            setGameOptionScreen();
            GUIImages.animation.stop();
        }));
        this.add(new InvisibleButton(GUIImages.optionsButtonImage, GUIImages.optionsButtonPressedImage, 25, 325, e -> setOptionScreen()));
        this.add(new InvisibleButton(GUIImages.aboutButtonImage, GUIImages.aboutButtonPressedImage, 25, 390, e -> setAboutScreen()));
        this.add(new InvisibleButton(GUIImages.quitButtonImage, GUIImages.quitButtonPressedImage, 605, 427, e -> System.exit(0)));
        this.add(GUIImages.titleScreen);
        this.add(GUIImages.animation);
        this.add(GUIImages.backgroundScreen);

        this.revalidate();
        this.repaint();

        startAnimation();
    }

    public void setOptionScreen() {
        this.getContentPane().removeAll();
        this.add(GUIImages.animation);

        this.add(new InvisibleButton(GUIImages.backButtonImage, GUIImages.backButtonPressedImage, 30, 395, e -> setTitleScreen()));
        this.add(GUIImages.optionsScreen);
        this.add(GUIImages.animation);
        this.add(GUIImages.backgroundDimmedBlurredScreen);

        this.revalidate();
        this.repaint();

        startAnimation();
    }

    public void setAboutScreen() {
        this.getContentPane().removeAll();

        this.add(new InvisibleButton(GUIImages.backButtonImage, GUIImages.backButtonPressedImage, 560, 360, e -> setTitleScreen()));
        this.add(GUIImages.aboutScreen);
        this.add(GUIImages.animation);
        this.add(GUIImages.backgroundDimmedBlurredScreen);

        this.revalidate();
        this.repaint();

        startAnimation();
    }

    public void setGameOptionScreen() {
        this.getContentPane().removeAll();

        var whiteTimeField = new InvisibleNumberTextField(this, 25, 177, 196, 145, 30);
        var whiteIncrementField = new InvisibleNumberTextField(this, 25, 487, 196, 145, 30);
        var blackTimeField = new InvisibleNumberTextField(this, 25, 177, 243, 145, 30);
        var blackIncrementField = new InvisibleNumberTextField(this, 25, 487, 243, 145, 30);

        this.add(whiteTimeField);
        this.add(whiteIncrementField);
        this.add(blackTimeField);
        this.add(blackIncrementField);

        this.add(new InvisibleButton(GUIImages.startButtonImage, GUIImages.startButtonPressedImage, 430, 335, new StartGame(whiteTimeField, whiteIncrementField, blackTimeField, blackIncrementField,
                GUIImages.selectionIndicator, GUIImages.movementIndicator, GUIImages.attackIndicator, GUIImages.enPassantIndicator, GUIImages.checkIndicator, GUIImages.clockSpriteSheet, GUIImages.gameScreen)));
        this.add(new InvisibleButton(GUIImages.backButtonImage, GUIImages.backButtonPressedImage, 30, 395, e -> setTitleScreen()));
        this.add(GUIImages.gameOptionsScreen);
        this.add(GUIImages.backgroundDimmedBlurredScreen);

        this.revalidate();
        this.repaint();
    }

    public void setGameScreen(BoardFactory chessBoardPanel, SideBarFactory chessSidePanel, JLabel gameScreenImage) {
        this.getContentPane().removeAll();

        this.currentChessBoardPanel = chessBoardPanel;
        this.currentChessSidePanel = chessSidePanel;
        this.currentGameScreen = gameScreenImage;

        this.add(chessBoardPanel);
        this.add(chessSidePanel);
        this.add(currentGameScreen);

        this.revalidate();
        this.repaint();
    }

    public void returnToGameScreen(boolean returnToCurrentDisplay) {
        this.getContentPane().removeAll();

        this.add(currentChessBoardPanel);
        this.add(currentChessSidePanel);

        if (returnToCurrentDisplay)
            this.add(currentGameScreen);
        else
            this.add(GUIImages.gameScreen);

        this.revalidate();
        this.repaint();
    }

    public void setPawnPromotionScreen(PieceColor color, int xCoord, int yCoord) {
        this.getContentPane().removeAll();

        var pawnPromotionPanel = new JPanel(null);
        pawnPromotionPanel.setBounds(0, 0, 660, 480);
        pawnPromotionPanel.setOpaque(false);

        if (color == PieceColor.BLACK) {
            GUIImages.blackPawnPromotionBox.setBounds(xCoord * 60, yCoord * 60 - 60, 240, 60);

            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60, yCoord * 60 - 60, 60, 60, e -> Promote.promote(PieceType.QUEEN, color, xCoord, yCoord)));
            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60 + 60, yCoord * 60 - 60, 60, 60, e -> Promote.promote(PieceType.ROOK, color, xCoord, yCoord)));
            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60 + 120, yCoord * 60 - 60, 60, 60, e -> Promote.promote(PieceType.KNIGHT, color, xCoord, yCoord)));
            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60 + 180, yCoord * 60 - 60, 60, 60, e -> Promote.promote(PieceType.BISHOP, color, xCoord, yCoord)));
            pawnPromotionPanel.add(GUIImages.blackPawnPromotionBox);
        } else {
            GUIImages.whitePawnPromotionBox.setBounds(xCoord * 60, yCoord * 60 + 60, 240, 60);

            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60, yCoord * 60 + 60, 60, 60, e -> Promote.promote(PieceType.QUEEN, color, xCoord, yCoord)));
            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60 + 60, yCoord * 60 + 60, 60, 60, e -> Promote.promote(PieceType.ROOK, color, xCoord, yCoord)));
            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60 + 120, yCoord * 60 + 60, 60, 60, e -> Promote.promote(PieceType.KNIGHT, color, xCoord, yCoord)));
            pawnPromotionPanel.add(new InvisibleButton(xCoord * 60 + 180, yCoord * 60 + 60, 60, 60, e -> Promote.promote(PieceType.BISHOP, color, xCoord, yCoord)));
            pawnPromotionPanel.add(GUIImages.whitePawnPromotionBox);
        }

        this.add(pawnPromotionPanel);
        this.add(currentChessBoardPanel);
        this.add(currentChessSidePanel);
        this.add(currentGameScreen);

        this.revalidate();
        this.repaint();
    }

    public void setEndScreen(ImageIcon gif, String message) {
        this.getContentPane().removeAll();

        // disables board and removes visual indicators
        BoardFactory.setGameStarted(false);
        BoardFactory.disableTiles();
        Deselect.deselectAll();
        Indicators.forceRemoveAllIndicators();

        // disables timers, clocks, and buttons
        SideBarFactory.disableButtons();
        SideBarFactory.getBlackTimer().stop();
        SideBarFactory.getWhiteTimer().stop();

        this.add(new GIFLabel(gif, 90, 0, 0, 720, 480)); // adds and plays a gif
        this.add(new GUILabel(this, message, 25, 3, 0, 150, 720, 75)); // adds text explaining the outcome of the match
        this.add(new InvisibleButton(GUIImages.restartButtonImage, GUIImages.restartButtonPressedImage, 207, 255, e -> setGameOptionScreen()));
        this.add(new BlurLabel(100, 0, 0, 720, 480));
        this.add(currentChessBoardPanel);
        this.add(currentChessSidePanel);
        this.add(currentGameScreen);

        this.revalidate();
        this.repaint();
    }

    public static void startAnimation() {
        if (!GUIImages.animation.isCurrentlyOn())
            GUIImages.animation.start(15, 500);
    }
}

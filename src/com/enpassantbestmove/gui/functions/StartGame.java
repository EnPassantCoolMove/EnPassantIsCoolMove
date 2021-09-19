package com.enpassantbestmove.gui.functions;

import com.enpassantbestmove.Main;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.general.BackgroundLabel;
import com.enpassantbestmove.gui.general.InvisibleNumberTextField;
import com.enpassantbestmove.gui.sidebar.SideBarFactory;
import com.enpassantbestmove.pieces.layout.StandardLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class StartGame implements ActionListener {
    private InvisibleNumberTextField whiteTimeField;
    private InvisibleNumberTextField whiteIncrementField;
    private InvisibleNumberTextField blackTimeField;
    private InvisibleNumberTextField blackIncrementField;
    private ImageIcon selectionIndicator;
    private ImageIcon movementIndicator;
    private ImageIcon attackIndicator;
    private ImageIcon enPassantIndicator;
    private ImageIcon checkIndicator;
    private BufferedImage clockSpriteSheet;
    private BackgroundLabel gameScreen;

    public StartGame(InvisibleNumberTextField whiteTimeField,
                     InvisibleNumberTextField whiteIncrementField,
                     InvisibleNumberTextField blackTimeField,
                     InvisibleNumberTextField blackIncrementField,
                     ImageIcon selectionIndicator,
                     ImageIcon movementIndicator,
                     ImageIcon attackIndicator,
                     ImageIcon enPassantIndicator,
                     ImageIcon checkIndicator,
                     BufferedImage clockSpriteSheet,
                     BackgroundLabel gameScreen
    ) {
        this.whiteTimeField = whiteTimeField;
        this.whiteIncrementField = whiteIncrementField;
        this.blackTimeField = blackTimeField;
        this.blackIncrementField = blackIncrementField;
        this.selectionIndicator = selectionIndicator;
        this.movementIndicator = movementIndicator;
        this.attackIndicator = attackIndicator;
        this.enPassantIndicator = enPassantIndicator;
        this.checkIndicator = checkIndicator;
        this.clockSpriteSheet = clockSpriteSheet;
        this.gameScreen = gameScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            var whiteTime = Integer.parseInt(whiteTimeField.getText()) * 60;
            var blackTime = Integer.parseInt(blackTimeField.getText()) * 60;
            int whiteIncrement;
            int blackIncrement;

            try {
                whiteIncrement = Integer.parseInt(whiteIncrementField.getText());
            } catch (Exception exception) {
                whiteIncrement = 0;
            }
            try {
                blackIncrement = Integer.parseInt(blackIncrementField.getText());
            } catch (Exception exception) {
                blackIncrement = 0;
            }

            Main.getCurrentScreen().setGameScreen(
                    new BoardFactory(
                            8, 8,
                            new StandardLayout(),
                            selectionIndicator,
                            movementIndicator,
                            attackIndicator,
                            enPassantIndicator,
                            checkIndicator),
                    new SideBarFactory(clockSpriteSheet, whiteTime, whiteIncrement, blackTime, blackIncrement),
                    gameScreen
            );
        } catch (Exception ignored) {}
    }
}

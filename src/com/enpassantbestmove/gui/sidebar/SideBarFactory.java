package com.enpassantbestmove.gui.sidebar;

import com.enpassantbestmove.gamerules.EndOfGame;
import com.enpassantbestmove.gui.GUIImages;
import com.enpassantbestmove.gui.general.GUILabel;
import com.enpassantbestmove.gui.general.InvisibleButton;
import com.enpassantbestmove.pieces.PieceColor;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class SideBarFactory extends JPanel {
    private static AdvantageBar advantageBar;
    private static AdvantageDisplay advantageDisplay;
    private static ChessTimer blackTimer;
    private static ChessTimer whiteTimer;
    private static AnimatedClock blackClock;
    private static AnimatedClock whiteClock;

    private static InvisibleButton blackResignButton;
    private static InvisibleButton whiteResignButton;
    private static InvisibleButton blackDrawButton;
    private static InvisibleButton whiteDrawButton;
    private static InvisibleButton acceptDrawButton;
    private static InvisibleButton declineDrawButton;
    private static GUILabel blackDrawOfferLabel;
    private static GUILabel whiteDrawOfferLabel;

    public SideBarFactory(BufferedImage clockSpriteSheet, int whiteTimeSeconds, int whiteIncrementSeconds, int blackTimeSeconds, int blackIncrementSeconds) {
        this.setBounds(480, 0, 240, 480);
        this.setOpaque(false);
        this.setLayout(null);

        advantageBar = new AdvantageBar();

        advantageDisplay = new AdvantageDisplay();

        blackTimer = new ChessTimer(this, PieceColor.BLACK, blackTimeSeconds, blackIncrementSeconds, 105, 27, 110, 30, 24);
        whiteTimer = new ChessTimer(this, PieceColor.WHITE, whiteTimeSeconds, whiteIncrementSeconds, 107, 421, 110, 30, 24);
        blackTimer.start();
        whiteTimer.start();

        blackClock = new AnimatedClock(clockSpriteSheet, 27, 15);
        whiteClock = new AnimatedClock(clockSpriteSheet, 30, 406);

        blackResignButton = new InvisibleButton(GUIImages.resignButtonImage, GUIImages.resignButtonPressedImage,130, 85, e -> EndOfGame.resignation(PieceColor.BLACK));
        blackDrawButton = new InvisibleButton(GUIImages.drawButtonImage, GUIImages.drawButtonPressedImage, 30, 85, e -> EndOfGame.offerDraw(PieceColor.BLACK));
        whiteResignButton = new InvisibleButton(GUIImages.resignButtonImage, GUIImages.resignButtonPressedImage, 130, 340, e -> EndOfGame.resignation(PieceColor.WHITE));
        whiteDrawButton = new InvisibleButton(GUIImages.drawButtonImage, GUIImages.drawButtonPressedImage, 30, 340, e -> EndOfGame.offerDraw(PieceColor.WHITE));

        acceptDrawButton = new InvisibleButton(GUIImages.acceptButtonImage, GUIImages.acceptButtonPressedImage, 60, 245, e -> EndOfGame.acceptDraw());
        acceptDrawButton.setEnabled(false);
        acceptDrawButton.setVisible(false);
        declineDrawButton = new InvisibleButton(GUIImages.declineButtonImage, GUIImages.declineButtonPressedImage, 143, 245, e -> EndOfGame.delcineDraw());
        declineDrawButton.setEnabled(false);
        declineDrawButton.setVisible(false);

        blackDrawOfferLabel = new GUILabel(this, "<html><div style='text-align: center;'>" + "Black has offered<br>a Draw" + "</div></html>", 20, 2, 40, 160, 200, 100);
        blackDrawOfferLabel.setVisible(false);
        blackDrawOfferLabel.getForegroundText().setVisible(false);
        whiteDrawOfferLabel = new GUILabel(this, "<html><div style='text-align: center;'>" + "White has offered<br>a Draw" + "</div></html>", 20, 2, 40, 160, 200, 100);
        whiteDrawOfferLabel.setVisible(false);
        whiteDrawOfferLabel.getForegroundText().setVisible(false);

        this.add(advantageBar);
        this.add(advantageDisplay);
        this.add(blackTimer);
        this.add(whiteTimer);
        this.add(blackClock);
        this.add(whiteClock);

        this.add(blackResignButton);
        this.add(blackDrawButton);
        this.add(whiteResignButton);
        this.add(whiteDrawButton);
        this.add(acceptDrawButton);
        this.add(declineDrawButton);
        this.add(blackDrawOfferLabel);
        this.add(whiteDrawOfferLabel);
    }

    public static void disableButtons() {
        blackResignButton.setEnabled(false);
        whiteResignButton.setEnabled(false);
        blackDrawButton.setEnabled(false);
        whiteDrawButton.setEnabled(false);
        acceptDrawButton.setEnabled(false);
        declineDrawButton.setEnabled(false);
    }

    public static AdvantageBar getAdvantageBar() {
        return advantageBar;
    }

    public static AdvantageDisplay getAdvantageDisplay() {
        return advantageDisplay;
    }

    public static ChessTimer getBlackTimer() {
        return blackTimer;
    }

    public static ChessTimer getWhiteTimer() {
        return whiteTimer;
    }

    public static AnimatedClock getBlackClock() {
        return blackClock;
    }

    public static AnimatedClock getWhiteClock() {
        return whiteClock;
    }

    public static InvisibleButton getBlackResignButton() {
        return blackResignButton;
    }

    public static InvisibleButton getWhiteResignButton() {
        return whiteResignButton;
    }

    public static InvisibleButton getBlackDrawButton() {
        return blackDrawButton;
    }

    public static InvisibleButton getWhiteDrawButton() {
        return whiteDrawButton;
    }

    public static InvisibleButton getAcceptDrawButton() {
        return acceptDrawButton;
    }

    public static InvisibleButton getDeclineDrawButton() {
        return declineDrawButton;
    }

    public static GUILabel getBlackDrawOfferLabel() {
        return blackDrawOfferLabel;
    }

    public static GUILabel getWhiteDrawOfferLabel() {
        return whiteDrawOfferLabel;
    }
}

package com.enpassantbestmove.gui.sidebar;

import com.enpassantbestmove.gamerules.EndOfGame;
import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.GUIColors;
import com.enpassantbestmove.gui.general.InvisibleLabel;
import com.enpassantbestmove.pieces.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ChessTimer extends InvisibleLabel {
    private int millisecondsAllowed;
    private final int millisecondsOfIncrement;
    private final InvisibleLabel foregroundText;

    private final Timer timer;
    private final TimerTask task;

    public ChessTimer(JFrame frame, PieceColor turn, int secondsAllowed, int secondsOfIncrement, int x, int y, int width, int height, int textSize) {
        super(textSize, x, y, width, height);
        this.millisecondsAllowed = secondsAllowed * 1000;
        this.millisecondsOfIncrement = secondsOfIncrement * 1000;

        foregroundText = new InvisibleLabel(textSize, x - 2, y, width, height);
        foregroundText.setForeground(GUIColors.OFF_WHITE);
        update();
        frame.add(foregroundText);

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (BoardFactory.getTurn() == turn && BoardFactory.isGameStarted() && millisecondsAllowed > 0) {
                    millisecondsAllowed -= 100;
                    update();
                    if (turn == PieceColor.WHITE)
                        SideBarFactory.getWhiteClock().update();
                    else
                        SideBarFactory.getBlackClock().update();
                } else if (millisecondsAllowed == 0) {
                    EndOfGame.timeout(turn);
                }
            }
        };
    }

    public ChessTimer(JPanel panel, PieceColor turn, int secondsAllowed, int secondsOfIncrement, int x, int y, int width, int height, int textSize) {
        super(textSize, x, y, width, height);
        this.millisecondsAllowed = secondsAllowed * 1000;
        this.millisecondsOfIncrement = secondsOfIncrement * 1000;

        foregroundText = new InvisibleLabel(textSize, x - 2, y, width, height);
        foregroundText.setForeground(GUIColors.OFF_WHITE);
        panel.add(foregroundText);

        update();

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (BoardFactory.getTurn() == turn && BoardFactory.isGameStarted() && millisecondsAllowed > 0) {
                    millisecondsAllowed -= 100;
                    update();
                    if (turn == PieceColor.WHITE)
                        SideBarFactory.getWhiteClock().update();
                    else
                        SideBarFactory.getBlackClock().update();
                } else if (millisecondsAllowed == 0) {
                    EndOfGame.timeout(turn);
                }
            }
        };
    }

    public void update() {
        var hoursLeft = millisecondsAllowed / 3_600_000;
        var minutesLeft = (millisecondsAllowed / 60_000) % 60;
        var secondsLeft = millisecondsAllowed / 1000 % 60;
        var tenthOfSecondsLeft = (millisecondsAllowed / 100) % 10;
        String formattedHours;
        String formattedMinutes;
        String formattedSeconds;
        String formattedTenthOfSeconds;

        if (hoursLeft > 0) {
            formattedHours = hoursLeft + ":";
            formattedMinutes = String.format("%02d", minutesLeft);
            formattedTenthOfSeconds = "";
            this.setForeground(Color.BLACK);
        } else if (millisecondsAllowed > 20_000){
            formattedHours = "";
            formattedMinutes = Integer.toString(minutesLeft);
            formattedTenthOfSeconds = "";
            this.setForeground(Color.BLACK);
        } else {
            formattedHours = "";
            formattedMinutes = Integer.toString(minutesLeft);
            formattedTenthOfSeconds = ":" + tenthOfSecondsLeft;
            this.setForeground(Color.RED);
        }
        formattedSeconds = String.format("%02d", secondsLeft);

        this.setText(formattedHours + formattedMinutes + ":" + formattedSeconds + formattedTenthOfSeconds);
        foregroundText.setText(this.getText());
    }

    public void start() {
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }

    public int getMillisecondsAllowed() {
        return millisecondsAllowed;
    }

    public int getMillisecondsOfIncrement() {
        return millisecondsOfIncrement;
    }

    public void setMillisecondsAllowed(int millisecondsAllowed) {
        this.millisecondsAllowed = millisecondsAllowed;
    }
}

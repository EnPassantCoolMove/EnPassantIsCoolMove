package com.enpassantbestmove.gui.titlescreenanimation;

import com.enpassantbestmove.gui.GUIImages;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class CreateAnimation extends JPanel{
    private Timer timer;
    private TimerTask movementTask;
    private List<FallingPawnLabel> pawns;

    private boolean currentlyOn;

    public CreateAnimation() {
        this.setBounds(0, 0, 720, 480);
        this.setOpaque(false);

        pawns = new ArrayList<>();
        timer = new java.util.Timer();
        movementTask = new TimerTask() {
            @Override
            public void run() {
                synchronized (CreateAnimation.this) {
                    for (FallingPawnLabel pawn : pawns) {
                        pawn.fall();
                    }
                }
            }
        };
    }

    public void start(int numberOfPawns, int millisecondsBetweenInitialSpawn) {
        currentlyOn = true;

        new Thread(() -> {
            for (int i = 0; i < numberOfPawns; i++) {
                var pawn = new FallingPawnLabel(GUIImages.blurredPawnImage);
                synchronized (CreateAnimation.this) {
                    pawns.add(pawn);
                }
                CreateAnimation.this.add(pawn);
                try {
                    Thread.sleep(millisecondsBetweenInitialSpawn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            timer.scheduleAtFixedRate(movementTask, 0, 33);
        } catch(Exception ignored) {}
    }

    public void stop() {
        currentlyOn = false;
        synchronized (this) {
            pawns.removeAll(pawns);
        }
    }

    public boolean isCurrentlyOn() {
        return currentlyOn;
    }
}

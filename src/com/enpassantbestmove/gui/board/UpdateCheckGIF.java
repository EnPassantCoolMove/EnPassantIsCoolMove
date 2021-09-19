package com.enpassantbestmove.gui.board;

import java.awt.*;
import java.util.Arrays;

public class UpdateCheckGIF implements Runnable{

    @Override
    public void run() {
        while (true) {
            if (BoardFactory.isGameStarted()) {
                Arrays.stream(BoardFactory.getBoard())
                        .flatMap(Arrays::stream)
                        .filter(BoardTile::isCheckIndicatorOn)
                        .forEach(Component::repaint);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

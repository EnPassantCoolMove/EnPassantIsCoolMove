package com.enpassantbestmove;

import com.enpassantbestmove.gui.Screen;
import com.enpassantbestmove.gui.board.UpdateCheckGIF;

public class Main {
    private static Screen currentScreen;

    public static void main(String[] args) {
        currentScreen = new Screen();
        new Thread(new UpdateCheckGIF()).start();
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }
}

package com.enpassantbestmove.gui;

import com.enpassantbestmove.gui.general.BackgroundLabel;
import com.enpassantbestmove.gui.titlescreenanimation.CreateAnimation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUIImages {
    // background screens
    final public static BackgroundLabel backgroundScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/BackgroundRegular.png")), 0, 0);
    final public static BackgroundLabel backgroundDimmedBlurredScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/BackgroundDimmedBlurred.png")), 0, 0);
    final public static BackgroundLabel titleScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/TitleScreen.png")), 0, 0);
    final public static BackgroundLabel gameOptionsScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/GameOptionScreen.png")), 0, 0);
    final public static BackgroundLabel gameScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/GameScreen.png")), 0, 0);
    final public static BackgroundLabel optionsScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/OptionScreen.png")), 0, 0);
    final public static BackgroundLabel aboutScreen = new BackgroundLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/backgrounds/AboutScreen.png")), 0, 0);

    // button idle icons
    final public static ImageIcon aboutButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/AboutButton.png"));
    final public static ImageIcon acceptButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/AcceptButton.png"));
    final public static ImageIcon backButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/BackButton.png"));
    final public static ImageIcon declineButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/DeclineButton.png"));
    final public static ImageIcon drawButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/DrawButton.png"));
    final public static ImageIcon optionsButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/OptionsButton.png"));
    final public static ImageIcon quitButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/QuitButton.png"));
    final public static ImageIcon resignButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/ResignButton.png"));
    final public static ImageIcon restartButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/RestartButton.png"));
    final public static ImageIcon startButtonImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/idle/StartButton.png"));

    // button pressed icons
    final public static ImageIcon aboutButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/AboutButtonPressed.png"));
    final public static ImageIcon acceptButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/AcceptButtonPressed.png"));
    final public static ImageIcon backButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/BackButtonPressed.png"));
    final public static ImageIcon declineButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/DeclineButtonPressed.png"));
    final public static ImageIcon drawButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/DrawButtonPressed.png"));
    final public static ImageIcon optionsButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/OptionsButtonPressed.png"));
    final public static ImageIcon quitButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/QuitButtonPressed.png"));
    final public static ImageIcon resignButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/ResignButtonPressed.png"));
    final public static ImageIcon restartButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/RestartButtonPressed.png"));
    final public static ImageIcon startButtonPressedImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("screens/buttons/pressed/StartButtonPressed.png"));

    // indicator icons
    final public static ImageIcon selectionIndicator = new ImageIcon(GUIImages.class.getClassLoader().getResource("indicators/SelectedIndicator.png"));
    final public static ImageIcon movementIndicator = new ImageIcon(GUIImages.class.getClassLoader().getResource("indicators/MovementIndicator.png"));
    final public static ImageIcon attackIndicator = new ImageIcon(GUIImages.class.getClassLoader().getResource("indicators/AttackIndicator.png"));
    final public static ImageIcon enPassantIndicator = new ImageIcon(GUIImages.class.getClassLoader().getResource("indicators/AttackIndicator.png"));
    final public static ImageIcon checkIndicator = new ImageIcon(GUIImages.class.getClassLoader().getResource("indicators/CheckIndicator.gif"));

    // sudo popups
    final public static JLabel blackPawnPromotionBox = new JLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("components/BlackPawnPromotion.png")));
    final public static JLabel whitePawnPromotionBox = new JLabel(new ImageIcon(GUIImages.class.getClassLoader().getResource("components/WhitePawnPromotion.png")));

    // sprite sheets
    public static BufferedImage clockSpriteSheet = null;
    static {
        try {
            clockSpriteSheet = ImageIO.read(GUIImages.class.getClassLoader().getResource("animations/sidebar/ClockSpriteSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // animation image
    final public static ImageIcon blurredPawnImage = new ImageIcon(GUIImages.class.getClassLoader().getResource("animations/titlescreen/BlurredPawn.png"));

    // animation
    final public static CreateAnimation animation = new CreateAnimation();
}

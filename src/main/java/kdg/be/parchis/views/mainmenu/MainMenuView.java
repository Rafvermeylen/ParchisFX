package kdg.be.parchis.views.mainmenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import kdg.be.parchis.model.musicLogic.Music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenuView extends BorderPane {
    private Button startButton;
    private Button leaderboards;
    private Button credits;
    private Button rules;
    private Button cheat;
    private Button exit;
    private Button soundButton;
    private Button fxButton;
    private Image background;
    private Image musicLoud;
    private Image musicMute;
    private Image sfxLoud;
    private Image sfxMute;
    private ImageView im;
    private ImageView musicPic;
    private ImageView soundPic;

    public MainMenuView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));
        Image logo = new Image(new FileInputStream("resources\\graphics\\logo.png"));
        im = new ImageView(logo);

        musicLoud = new Image(new FileInputStream("resources/graphics/buttons/mute/MuteButton_loud.png"));
        musicMute = new Image(new FileInputStream("resources/graphics/buttons/mute/MuteButton_muted.png"));

        sfxLoud = new Image(new FileInputStream("resources/graphics/buttons/mute/SFX_loud.png"));
        sfxMute = new Image(new FileInputStream("resources/graphics/buttons/mute/SFX_muted.png"));

        musicPic = new ImageView(musicLoud);
        soundPic = new ImageView(sfxLoud);

        double r = 1.5;
        soundButton = new Button("", musicPic);
        fxButton = new Button("", soundPic);

        soundButton.setShape(new Circle(r));
        fxButton.setShape(new Circle(r));

        soundButton.setMinSize(50, 50);
        soundButton.setMaxSize(50, 50);

        fxButton.setMinSize(50, 50);
        fxButton.setMaxSize(50, 50);

        startButton = new Button("Start game");
        leaderboards = new Button("Leaderboards");
        credits = new Button("Credits");
        rules = new Button("Rules");
        cheat = new Button("Cheats: OFF");
        exit = new Button("Exit");
    }

    private void layoutNodes() {
        startButton.setMaxWidth(150);
        leaderboards.setMaxWidth(150);
        credits.setMaxWidth(150);
        rules.setMaxWidth(150);
        cheat.setMaxWidth(150);

        HBox buttonsTop = new HBox(15);
        buttonsTop.getChildren().addAll(soundButton, fxButton);
        buttonsTop.setAlignment(Pos.TOP_RIGHT);

        VBox buttonsMiddle = new VBox(15);
        buttonsMiddle.getChildren().addAll(im, startButton, leaderboards, credits, rules, cheat);
        buttonsMiddle.setAlignment(Pos.TOP_CENTER);

        this.setCenter(buttonsMiddle);
        this.setTop(buttonsTop);
        this.setBottom(exit);

        BorderPane.setAlignment(buttonsMiddle, Pos.CENTER);
        BorderPane.setAlignment(im, Pos.TOP_CENTER);
        BorderPane.setAlignment(exit, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(exit, new Insets(30));

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Music.playMenuMusic();
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getLeaderboards() {
        return leaderboards;
    }

    public Button getCredits() {
        return credits;
    }

    public Button getCheat() {
        return cheat;
    }

    public Button getExit() {
        return exit;
    }

    public Button getSoundButton() {
        return soundButton;
    }

    public Button getFxButton() {
        return fxButton;
    }

    public Image getMusicLoud() {
        return musicLoud;
    }

    public Image getSfxLoud() {
        return sfxLoud;
    }

    public ImageView getMusicPic() {
        return musicPic;
    }

    public ImageView getSoundPic() {
        return soundPic;
    }

    public Image getMusicMute() {
        return musicMute;
    }

    public Image getSfxMute() {
        return sfxMute;
    }

    public Button getRules() {
        return rules;
    }
}
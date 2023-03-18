package kdg.be.parchis.views.credits;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class CreditsView extends BorderPane {
    public static final String MONOSPACED = "Monospaced";
    private Button soundButton;
    private Button fxButton;
    private Image musicLoud;
    private Image musicMute;
    private Image sfxLoud;
    private Image sfxMute;
    private ImageView musicPic;
    private ImageView soundPic;
    private Button back;
    private Image background;
    private Text credits;
    private Label title;

    public CreditsView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        title = new Label("\nCredits");
        credits = new Text();
        background = new Image(new FileInputStream("resources\\backgrounds\\background_leaderboard.png"));

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
    }

    private void layoutNodes() {
        credits.setText("""
                Creators:
                - Rui Daniel Gomes Vieira
                - Raf Vermeylen
                Game Testers: 
                - Akino Verschueren
                - Pieter Corstens
                - Robby Crijns
                - Niels Haegemans
                - Joel Chapon
                Optimisation Wizard:
                - Pierre Franck"""
        );
        credits.setFont(Font.font(MONOSPACED, 24));
        title.setScaleX(3);
        title.setScaleY(3);

        HBox buttonsTop = new HBox(15);
        buttonsTop.getChildren().addAll(soundButton, fxButton);
        buttonsTop.setAlignment(Pos.TOP_RIGHT);

        VBox vBox = new VBox(50);
        vBox.getChildren().addAll(title, credits, back);
        vBox.setAlignment(Pos.CENTER);

        this.setTop(buttonsTop);
        this.setCenter(vBox);
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setMargin(back, new Insets(90));
    }

    public Button getBack() {
        return back;
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
}


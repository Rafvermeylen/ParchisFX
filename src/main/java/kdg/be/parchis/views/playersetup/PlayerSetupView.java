package kdg.be.parchis.views.playersetup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.UnaryOperator;

public class PlayerSetupView extends BorderPane {
    private Button soundButton;
    private Button fxButton;
    private Image musicLoud;
    private Image musicMute;
    private Image sfxLoud;
    private Image sfxMute;
    private ImageView musicPic;
    private ImageView soundPic;
    private Image background;
    private Button back;
    private Button play;
    private TextField player1NameArea;
    private TextField player2NameArea;
    private TextField player3NameArea;
    private TextField player4NameArea;
    private HBox setupNames;

    public PlayerSetupView () throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));

        player1NameArea = new TextField("");
        player2NameArea = new TextField("");
        player3NameArea = new TextField("");
        player4NameArea = new TextField("");
        setupNames = new HBox(20);

        play = new Button("Play!");

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
        player1NameArea.setPrefSize(200,10);
        player2NameArea.setPrefSize(200,10);
        player3NameArea.setPrefSize(200,10);
        player4NameArea.setPrefSize(200,10);

        // Set character limit to 14
        UnaryOperator<TextFormatter.Change> lengthFilter = change -> {
            int newLength = change.getControlNewText().length();
            if (newLength <= 10) {
                return change;
            } else {
                return null;
            }
        };
        StringConverter<String> converter = new DefaultStringConverter();
        TextFormatter<String> textFormatter1 = new TextFormatter<>(converter, "", lengthFilter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(converter, "", lengthFilter);
        TextFormatter<String> textFormatter3 = new TextFormatter<>(converter, "", lengthFilter);
        TextFormatter<String> textFormatter4 = new TextFormatter<>(converter, "", lengthFilter);

        player1NameArea.setTextFormatter(textFormatter1);
        player2NameArea.setTextFormatter(textFormatter2);
        player3NameArea.setTextFormatter(textFormatter3);
        player4NameArea.setTextFormatter(textFormatter4);

        setupNames.setAlignment(Pos.CENTER);

        HBox buttonsTop = new HBox(15);
        buttonsTop.getChildren().addAll(soundButton, fxButton);
        buttonsTop.setAlignment(Pos.TOP_RIGHT);

        VBox vBox = new VBox(60);
        vBox.getChildren().addAll(setupNames, play);
        vBox.setAlignment(Pos.CENTER);

        this.setTop(buttonsTop);
        this.setCenter(vBox);
        this.setBottom(back);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        BorderPane.setAlignment(vBox, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));
    }

    public Button getBack() {
        return back;
    }

    public Button getPlay() {
        return play;
    }

    public TextField getPlayer1NameArea() {
        return player1NameArea;
    }

    public TextField getPlayer2NameArea() {
        return player2NameArea;
    }

    public TextField getPlayer3NameArea() {
        return player3NameArea;
    }

    public TextField getPlayer4NameArea() {
        return player4NameArea;
    }

    public HBox getSetupNames() {
        return setupNames;
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

package kdg.be.parchis.views.playerselect;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class PlayerSelectView extends BorderPane {
    private Button soundButton;
    private Button fxButton;
    private Image musicLoud;
    private Image musicMute;
    private Image sfxLoud;
    private Image sfxMute;
    private ImageView musicPic;
    private ImageView soundPic;
    private List<Button> players;
    private Image background;
    private Button back;

    public PlayerSelectView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        Image onePlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\one_player.png"));
        Image twoPlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\two_player.png"));
        Image threePlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\three_player.png"));
        Image fourPlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\four_player.png"));

        players = Arrays.asList(
                new Button("", new ImageView(onePlayerPic)),
                new Button("", new ImageView(twoPlayerPic)),
                new Button("", new ImageView(threePlayerPic)),
                new Button("", new ImageView(fourPlayerPic))
        );

        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));

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
        HBox hBox = new HBox(20);
        for (Button p : players) {
            p.setPrefSize(100, 100);
            hBox.getChildren().add(p);
        }

        hBox.setAlignment(Pos.CENTER);

        HBox buttonsTop = new HBox(15);
        buttonsTop.getChildren().addAll(soundButton, fxButton);
        buttonsTop.setAlignment(Pos.TOP_RIGHT);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.setTop(buttonsTop);
        this.setCenter(hBox);
        this.setBottom(back);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));
    }

    public List<Button> getPlayers() {
        return players;
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
package kdg.be.parchis.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ParchisMenuView extends StackPane {
    // private Node attributen (controls)
    private Label title;
    private Button startButton;
    private Button leaderboards;
    private Button cheat;
    private Button exit;

    public ParchisMenuView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
        this.menuMusic();
    }

    private void initialiseNodes() {
        title = new Label("PARCHIS!");
        startButton = new Button("Start game");
        leaderboards = new Button("Leaderboards");
        cheat = new Button("Cheats");
        exit = new Button("Exit");
// Initialisatie van de Nodes
// bvb.:
// button = new Button("...")
// label = new Label("...")
    }

    MediaPlayer mediaPlayer;
    private void menuMusic() {
        String path = "src\\main\\java\\kdg\\be\\parchis\\resources\\erika_fecked.mp3";
        //toURI transforms url to uri, which is converted to a string. It is needed to play audio.
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    private void layoutNodes() throws FileNotFoundException {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        startButton.setMaxWidth(150);
        leaderboards.setMaxWidth(150);
        cheat.setMaxWidth(150);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(startButton, leaderboards, cheat);
        layout.setAlignment(Pos.CENTER);

        VBox bottom = new VBox(10);
        bottom.getChildren().add(exit);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane top = new BorderPane();
        Image logo = new Image(new FileInputStream("src\\main\\java\\kdg\\be\\parchis\\resources\\logo_big.png"));
        ImageView im = new ImageView(logo);
        top.setTop(im);
        BorderPane.setAlignment(im, Pos.TOP_CENTER);

        this.getChildren().addAll(layout,top ,bottom);
        Image background = new Image(new FileInputStream("src\\main\\java\\kdg\\be\\parchis\\resources\\Background.jpg"));
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        // implementatie van de nodige
        // package-private Getters
    }
}
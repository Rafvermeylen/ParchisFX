package kdg.be.parchis.views.mainmenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenuView extends BorderPane {
    // private Node attributen (controls)
    private Button startButton;
    private Button leaderboards;
    private Button cheat;
    private Button exit;
    private MediaPlayer mediaPlayer;
    private Media menu_music;
    private Image logo;
    private ImageView imageViewLogo;


    public MainMenuView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        startButton = new Button("Start game");
        leaderboards = new Button("Leaderboards");
        cheat = new Button("Cheats: OFF");
        exit = new Button("Exit");
        menu_music = new Media(new File("resources\\audio\\menu_music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menu_music);



// Initialisatie van de Nodes
// bvb.:
// button = new Button("...")
// label = new Label("...")
    }

    private void layoutNodes() throws FileNotFoundException {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        startButton.setMaxWidth(150);
        leaderboards.setMaxWidth(150);
        cheat.setMaxWidth(150);

        VBox buttonsmiddle = new VBox(15);
        buttonsmiddle.getChildren().addAll(startButton, leaderboards, cheat);
        buttonsmiddle.setAlignment(Pos.CENTER);

        Image logo = new Image(new FileInputStream("resources\\graphics\\logo.png"));
        ImageView im = new ImageView(logo);

        this.setCenter(buttonsmiddle);
        this.setTop(im);
        this.setBottom(exit);

        BorderPane.setAlignment(buttonsmiddle, Pos.CENTER);
        BorderPane.setAlignment(im, Pos.TOP_CENTER);
        BorderPane.setAlignment(exit, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(exit, new Insets(30));

        Image background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        // implementatie van de nodige
        // package-private Getters
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getLeaderboards() {
        return leaderboards;
    }

    public Button getCheat() {
        return cheat;
    }

    public Button getExit() {
        return exit;
    }
}
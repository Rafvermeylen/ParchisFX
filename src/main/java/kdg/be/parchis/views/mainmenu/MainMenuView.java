package kdg.be.parchis.views.mainmenu;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import kdg.be.parchis.model.musicLogic.MainMusic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenuView extends BorderPane {
    private Button startButton;
    private Button leaderboards;
    private Button cheat;
    private Button exit;
    private Button soundButton;
    private Button fxButton;
    private Image logo;
    private ImageView imageViewLogo;


    public MainMenuView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        double r = 1.5;
        soundButton = new Button();
        fxButton = new Button();

        soundButton.setShape(new Circle(r));
        fxButton.setShape(new Circle(r));

        soundButton.setMinSize(50, 50);
        soundButton.setMaxSize(50, 50);

        fxButton.setMinSize(50, 50);
        fxButton.setMaxSize(50, 50);

        startButton = new Button("Start game");
        leaderboards = new Button("Leaderboards");
        cheat = new Button("Cheats: OFF");
        exit = new Button("Exit");
    }

    private void layoutNodes() throws FileNotFoundException {
        startButton.setMaxWidth(150);
        leaderboards.setMaxWidth(150);
        cheat.setMaxWidth(150);
        Image logo = new Image(new FileInputStream("resources\\graphics\\logo.png"));
        ImageView im = new ImageView(logo);

        HBox buttonsTop = new HBox(15);
        buttonsTop.getChildren().addAll(soundButton, fxButton);
        buttonsTop.setAlignment(Pos.TOP_RIGHT);

        VBox buttonsmiddle = new VBox(15);
        buttonsmiddle.getChildren().addAll(im, startButton, leaderboards, cheat);
        buttonsmiddle.setAlignment(Pos.TOP_CENTER);

        this.setCenter(buttonsmiddle);
        this.setTop(buttonsTop);
        this.setBottom(exit);

        BorderPane.setAlignment(buttonsmiddle, Pos.CENTER);
        BorderPane.setAlignment(im, Pos.TOP_CENTER);
        BorderPane.setAlignment(exit, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(exit, new Insets(30));

        Image background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        MainMusic.playMenuMusic();
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
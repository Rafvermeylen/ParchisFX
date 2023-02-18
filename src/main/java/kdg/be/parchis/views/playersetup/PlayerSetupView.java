package kdg.be.parchis.views.playersetup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musicLogic.MainMusic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerSetupView extends BorderPane {
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
    }
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        player1NameArea.setPrefSize(200,10);
        player2NameArea.setPrefSize(200,10);
        player3NameArea.setPrefSize(200,10);
        player4NameArea.setPrefSize(200,10);

        setupNames.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(60);
        vBox.getChildren().addAll(setupNames, play);
        vBox.setAlignment(Pos.CENTER);

        this.setCenter(vBox);
        this.setBottom(back);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        BorderPane.setAlignment(vBox, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));
    }
// implementatie van de nodige
// package-private Getters

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
}

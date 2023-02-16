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
    private Label amountPlayers;
    private PlayerSetup setup;
    private Button back;
    private Button play;
    private TextField player1NameArea;
    private TextField player2NameArea;
    private TextField player3NameArea;
    private TextField player4NameArea;


    public PlayerSetupView (PlayerSetup psetup) throws FileNotFoundException {
        this.setup = psetup;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));
        amountPlayers = new Label("You have selected "
                + setup.getAmountPlayers() + " players.");

        player1NameArea = new TextField("Player 1");
        if (setup.getAmountPlayers() > 1){
            player2NameArea = new TextField("Player 2");
        }
        if (setup.getAmountPlayers() > 2){
            player3NameArea = new TextField("Player 3");
        }
        if (setup.getAmountPlayers() > 3){
            player4NameArea = new TextField("Player 4");
        }
        play = new Button("Play!");
    }
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        player1NameArea.setPrefHeight(10);
        player1NameArea.setPrefWidth(200);

        HBox setupNames = new HBox(20);
        if (setup.getAmountPlayers()==1){
            setupNames.getChildren().add(player1NameArea);
        } else if (setup.getAmountPlayers()==2){
            player2NameArea.setPrefHeight(10);
            player2NameArea.setPrefWidth(200);
            setupNames.getChildren().addAll(player1NameArea, player2NameArea);
        }else if (setup.getAmountPlayers()==3){
            player2NameArea.setPrefHeight(10);
            player2NameArea.setPrefWidth(200);
            player3NameArea.setPrefHeight(10);
            player3NameArea.setPrefWidth(200);
            setupNames.getChildren().addAll(player1NameArea, player2NameArea, player3NameArea);
        }else if (setup.getAmountPlayers()==4){
            player2NameArea.setPrefHeight(10);
            player2NameArea.setPrefWidth(200);
            player3NameArea.setPrefHeight(10);
            player3NameArea.setPrefWidth(200);
            player4NameArea.setPrefHeight(10);
            player4NameArea.setPrefWidth(200);
            setupNames.getChildren().addAll(player1NameArea, player2NameArea, player3NameArea, player4NameArea);
        }
        setupNames.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(60);
        vBox.getChildren().addAll(setupNames, play);
        vBox.setAlignment(Pos.CENTER);

        this.setTop(amountPlayers);
        this.setCenter(vBox);
        this.setBottom(back);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        BorderPane.setAlignment(amountPlayers, Pos.TOP_CENTER);
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

    public PlayerSetup getSetup() {
        return setup;
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
}

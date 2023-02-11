package kdg.be.parchis.views.playerselect;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerSelectView extends HBox {
    private Button onePlayer;
    private Button twoPlayers;
    private Button threePlayers;
    private Button fourPlayers;
    private Image background;
    public PlayerSelectView() throws FileNotFoundException {
        super(20);
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
// Initialisatie van de Nodes
// bvb.:
// button = new Button("...")
// label = new Label("...")
        onePlayer = new Button("One Player");
        twoPlayers = new Button("Two Players");
        threePlayers = new Button("Three Players");
        fourPlayers = new Button("Four Players");
        background = new Image(new FileInputStream("resources\\Background.png"));
    }
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        onePlayer.setPrefSize(100,100);
        twoPlayers.setPrefSize(100,100);
        threePlayers.setPrefSize(100,100);
        fourPlayers.setPrefSize(100,100);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.getChildren().addAll(onePlayer, twoPlayers, threePlayers, fourPlayers);
        this.setAlignment(Pos.CENTER);
    }
// implementatie van de nodige
// package-private Getters


    public Button getOnePlayer() {
        return onePlayer;
    }

    public Button getTwoPlayers() {
        return twoPlayers;
    }

    public Button getThreePlayers() {
        return threePlayers;
    }

    public Button getFourPlayers() {
        return fourPlayers;
    }
}

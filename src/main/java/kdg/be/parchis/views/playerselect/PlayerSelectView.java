package kdg.be.parchis.views.playerselect;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerSelectView extends BorderPane {
    private Button onePlayer;
    private Button twoPlayers;
    private Button threePlayers;
    private Button fourPlayers;
    private Image background;
    private Button back;

    public PlayerSelectView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        onePlayer = new Button("One Player");
        twoPlayers = new Button("Two Players");
        threePlayers = new Button("Three Players");
        fourPlayers = new Button("Four Players");
        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));
    }
    private void layoutNodes() {
        HBox hBox = new HBox(20);
        onePlayer.setPrefSize(100,100);
        twoPlayers.setPrefSize(100,100);
        threePlayers.setPrefSize(100,100);
        fourPlayers.setPrefSize(100,100);
        hBox.getChildren().addAll(onePlayer, twoPlayers, threePlayers, fourPlayers);
        hBox.setAlignment(Pos.CENTER);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.setCenter(hBox);
        this.setBottom(back);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));
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

    public Button getBack() {
        return back;
    }
}

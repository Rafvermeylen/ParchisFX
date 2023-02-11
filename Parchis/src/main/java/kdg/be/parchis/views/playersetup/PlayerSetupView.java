package kdg.be.parchis.views.playersetup;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import kdg.be.parchis.model.Cheats;
import kdg.be.parchis.model.PlayerSetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerSetupView extends BorderPane {
    private Image background;
    private Label amountPlayers;
    private PlayerSetup pSetup;
    public PlayerSetupView (PlayerSetup psetup) throws FileNotFoundException {
        this.pSetup = psetup;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
// Initialisatie van de Nodes
// bvb.:
// button = new Button("...")
// label = new Label("...")
        background = new Image(new FileInputStream("resources\\Background.png"));
        amountPlayers = new Label("You have selected "
                + pSetup.getAmountPlayers() + " players.");
    }
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        this.setCenter(amountPlayers);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        BorderPane.setAlignment(amountPlayers, Pos.TOP_CENTER);
    }
// implementatie van de nodige
// package-private Getters
}

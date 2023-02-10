package kdg.be.parchis.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlayerSelect extends HBox {
    private Button onePlayer;
    private Button twoPlayers;
    private Button threePlayers;
    private Button fourPlayers;
    public PlayerSelect () {
        super(20);
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
// Initialisatie van de Nodes
// bvb.:
// button = new Button("...")
// label = new Label("...")
        onePlayer = new Button("One Player");
        twoPlayers = new Button("Two Players");
        threePlayers = new Button("Three Players");
        fourPlayers = new Button("Four Players");
    }
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        onePlayer.setPrefSize(100,100);
        twoPlayers.setPrefSize(100,100);
        threePlayers.setPrefSize(100,100);
        fourPlayers.setPrefSize(100,100);

        this.getChildren().addAll(onePlayer, twoPlayers, threePlayers, fourPlayers);
        this.setAlignment(Pos.CENTER);
    }
// implementatie van de nodige
// package-private Getters

}

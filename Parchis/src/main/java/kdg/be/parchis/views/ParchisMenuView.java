package kdg.be.parchis.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ParchisMenuView extends VBox
        {
    // private Node attributen (controls)
            private Label title;
            private Button startButton;
            private Button leaderboards;
            private Button cheat;
            private Button exit;

    public ParchisMenuView() {
        super(20);
        this.initialiseNodes();
        this.layoutNodes();
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
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        startButton.setMaxWidth(150);
        leaderboards.setMaxWidth(150);
        cheat.setMaxWidth(150);

        this.getChildren().add(title);
        this.getChildren().add(startButton);
        this.getChildren().add(leaderboards);
        this.getChildren().add(cheat);
        this.getChildren().add(exit);
        this.setAlignment(Pos.CENTER);
    }
// implementatie van de nodige
// package-private Getters
}
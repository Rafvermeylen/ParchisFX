package kdg.be.parchis.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ParchisMenuView extends BorderPane
        {
    // private Node attributen (controls)
            private Label title;
            private Button startButton;
            private Button leaderboards;
            private Button cheat;
            private Button exit;

    public ParchisMenuView() {
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
        this.setTop(title);
        this.setCenter(startButton);
        this.setBottom(exit);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);
        BorderPane.setAlignment(exit, Pos.TOP_CENTER);
        BorderPane.setMargin(title, new Insets(10,10,10,10));
        BorderPane.setMargin(exit, new Insets(10,10,10,10));
    }
// implementatie van de nodige
// package-private Getters
}
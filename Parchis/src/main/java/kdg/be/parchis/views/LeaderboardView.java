package kdg.be.parchis.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import kdg.be.parchis.model.Leaderboards;

public class LeaderboardView extends BorderPane {
    private Button back;
    private Label scores;
    private Leaderboards leaderboards;
    public LeaderboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        back = new Button("Back");
        scores = new Label();
        leaderboards = new Leaderboards();

// Initialisatie van de Nodes
// bvb.:
// button = new Button("...")
// label = new Label("...")
    }
    private void layoutNodes() {
        leaderboards.read();
        scores.setText(leaderboards.printScores());
        scores.setScaleX(2);scores.setScaleY(2);
        this.setCenter(scores);
        this.setBottom(back);

        BorderPane.setAlignment(scores, Pos.CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
    }
// implementatie van de nodige
// package-private Getters
}

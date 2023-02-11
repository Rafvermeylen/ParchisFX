package kdg.be.parchis.views.leaderboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import kdg.be.parchis.model.Leaderboards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class LeaderboardView extends BorderPane {
    private Button back;
    private Label scores;
    private Leaderboards leaderboards;
    private Image background;
    public LeaderboardView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        scores = new Label();
        leaderboards = new Leaderboards();
        background = new Image(new FileInputStream("resources\\background_leaderboard.png"));
    }
    private void layoutNodes() {
        leaderboards.read();
        scores.setText(leaderboards.printScores());
        scores.setScaleX(2);scores.setScaleY(2);
        this.setCenter(scores);
        this.setBottom(back);
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));


        BorderPane.setAlignment(scores, Pos.CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));

// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
    }
// implementatie van de nodige
// package-private Getters

    public Button getBack() {
        return back;
    }

    public Leaderboards getLeaderboards() {
        return leaderboards;
    }
}

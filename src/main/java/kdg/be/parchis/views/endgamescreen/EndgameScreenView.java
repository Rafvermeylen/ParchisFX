package kdg.be.parchis.views.endgamescreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class EndgameScreenView extends BorderPane {
    private Label scoreWinner;
    private Button backToMenu;
    public EndgameScreenView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        scoreWinner = new Label();
        backToMenu = new Button("Back to main menu");
    }

    private void layoutNodes() {
        this.setCenter(scoreWinner);
        this.setBottom(backToMenu);

        BorderPane.setAlignment(backToMenu, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(backToMenu, new Insets(30));
    }

    public Label getScoreWinner() {
        return scoreWinner;
    }

    public Button getBackToMenu() {
        return backToMenu;
    }
}

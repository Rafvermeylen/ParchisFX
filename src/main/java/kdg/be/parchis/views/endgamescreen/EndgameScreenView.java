package kdg.be.parchis.views.endgamescreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EndgameScreenView extends BorderPane {
    private Label scoreWinner;
    private Button backToMenu;
    private Image background;

    public EndgameScreenView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        scoreWinner = new Label();
        backToMenu = new Button("Back to main menu");
        background = new Image(new FileInputStream("resources\\backgrounds\\background_leaderboard.png"));
    }

    private void layoutNodes() {
        this.setCenter(scoreWinner);
        this.setBottom(backToMenu);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

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

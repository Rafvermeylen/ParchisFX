package kdg.be.parchis.views.leaderboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import kdg.be.parchis.model.menu.Leaderboards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.sun.javafx.font.LogicalFont.MONOSPACED;

public class LeaderboardView extends BorderPane {
    private Button back;
    private Text scores;
    private Label title;
    private Image background;
    public LeaderboardView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        scores = new Text();
        title = new Label("\n--TOP 5 HIGH SCORES--");
        background = new Image(new FileInputStream("resources\\backgrounds\\background_leaderboard.png"));
    }
    private void layoutNodes() {
        //Get leaderboards scores, then put it in label
        Leaderboards.read();
        scores.setText(Leaderboards.printScores());
        scores.setFont(Font.font(MONOSPACED, 25));
        title.setScaleX(3);
        title.setScaleY(3);

        VBox vBox = new VBox(100);
        vBox.getChildren().addAll(title ,scores, back);
        vBox.setAlignment(Pos.CENTER);

        this.setCenter(vBox);
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setMargin(back, new Insets(90));
    }
    public Button getBack() {
        return back;
    }

}

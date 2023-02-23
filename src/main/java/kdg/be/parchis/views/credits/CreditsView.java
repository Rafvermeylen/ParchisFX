package kdg.be.parchis.views.credits;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.sun.javafx.font.LogicalFont.MONOSPACED;

public class CreditsView extends BorderPane {
    private Button back;
    private Image background;
    private Text credits;
    private Label title;

    public CreditsView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        title = new Label("\nCredits");
        credits = new Text();
        background = new Image(new FileInputStream("resources\\backgrounds\\background_leaderboard.png"));
    }

    private void layoutNodes() {
        credits.setText("""
                Creators:
                - Rui Daniel Gomes Vieirra
                - Raf Vermeylen
                Game Testers: 
                - Andreas Baelus
                - Akino Verschueren
                - Wietse de Winter"""
        );
        credits.setFont(Font.font(MONOSPACED, 25));
        title.setScaleX(3);
        title.setScaleY(3);


        VBox vBox = new VBox(100);
        vBox.getChildren().addAll(title, credits, back);
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


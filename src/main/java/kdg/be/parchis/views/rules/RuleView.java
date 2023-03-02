package kdg.be.parchis.views.rules;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RuleView extends BorderPane{
    public static final String MONOSPACED = "Monospaced";
    private Button back;
    private Text rules;
    private Image background;

    public RuleView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        rules = new Text();
        background = new Image(new FileInputStream("resources\\backgrounds\\background_leaderboard.png"));

    }

    private void layoutNodes() {
        rules.setText("""
                \n\n\n\n\n\n
                1. When a five is thrown, you can 
                   get out of nest (starting place).
                   
                2. When a six is thrown, you can 
                   throw again (max. 3 times).
                   
                3. When all your pawns are out of 
                   nest, a six becomes a seven.
                   
                4. When you 'eat' a pawn from another 
                   player, you move 20 spaces (if possible).
                   
                5. When a pawn finishes, a pawn of 
                   your choosing can move 10 
                   spaces (if possible).
                   
                6. Tiles with a circle are safe spaces. 
                   Multiple pawns can land on them. If 2 pawns
                   of the same colour are on said tile, 
                   they create a barrier. It is impossible for
                   anybody to move further. If the owner 
                   of the barrier has thrown a 6 or 7, the 
                   barrier must be broken.
                   
                7. You have to throw the right 
                   amount of eyes to get to the finish.
                """

        );
        rules.setFont(Font.font(MONOSPACED, 15));


        VBox vBox = new VBox(50);
        vBox.getChildren().addAll(rules, back);
        vBox.setAlignment(Pos.CENTER);

        this.setCenter(vBox);
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setMargin(back, new Insets(20));
    }

    public Button getBack() {
        return back;
    }

}

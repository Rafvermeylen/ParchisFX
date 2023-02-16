package kdg.be.parchis.views.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

public class GameView extends BorderPane {
    private Label yellowPlayer;
    private Button roll1;
    private ImageView die1;
    private Button finish1;
    private Label bluePlayer;
    private Button roll2;
    private ImageView die2;
    private Button finish2;
    private Label redPlayer;
    private Button roll3;
    private ImageView die3;
    private Button finish3;
    private Label greenPlayer;
    private Button roll4;
    private ImageView die4;
    private Button finish4;
    private Image boardImg;
    private ImageView board;

    public GameView () throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        boardImg = new Image(new FileInputStream("resources\\graphics\\game\\board.png"));
        board = new ImageView(boardImg);

        yellowPlayer = new Label();
        bluePlayer = new Label();
        redPlayer = new Label();
        greenPlayer = new Label();


    }
    private void layoutNodes() {
// Layout van de Nodes
// add... methodes (of set...)
// Insets, padding, alignment, ...
        StackPane game = new StackPane();
        game.getChildren().add(board);

        game.setAlignment(Pos.CENTER);

        this.setCenter(game);
    }
// implementatie van de nodige
// package-private Getters


    public Label getYellowPlayer() {
        return yellowPlayer;
    }

    public Button getRoll1() {
        return roll1;
    }

    public ImageView getDie1() {
        return die1;
    }

    public Button getFinish1() {
        return finish1;
    }

    public Label getBluePlayer() {
        return bluePlayer;
    }

    public Button getRoll2() {
        return roll2;
    }

    public ImageView getDie2() {
        return die2;
    }

    public Button getFinish2() {
        return finish2;
    }

    public Label getRedPlayer() {
        return redPlayer;
    }

    public Button getRoll3() {
        return roll3;
    }

    public ImageView getDie3() {
        return die3;
    }

    public Button getFinish3() {
        return finish3;
    }

    public Label getGreenPlayer() {
        return greenPlayer;
    }

    public Button getRoll4() {
        return roll4;
    }

    public ImageView getDie4() {
        return die4;
    }

    public Button getFinish4() {
        return finish4;
    }
}

package kdg.be.parchis.views.game;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kdg.be.parchis.model.game.Die;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameView extends StackPane {
    private Label yellowPlayer;
    private Button roll1;
    private ImageView die1;
    private Button finish1;
    private Image yellowPawn;
    private ImageView yp_1;
    private ImageView yp_2;
    private ImageView yp_3;
    private ImageView yp_4;
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
    private Image background;
    private ImageView board;

    public GameView () throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        boardImg = new Image(new FileInputStream("resources\\graphics\\game\\board.png"));
        background = new Image(new FileInputStream("resources\\backgrounds\\ingame_background.png"));
        board = new ImageView(boardImg);

        yellowPlayer = new Label();
        yellowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_yellow.png"));
        yp_1 = new ImageView(yellowPawn);
        yp_2 = new ImageView(yellowPawn);
        yp_3 = new ImageView(yellowPawn);
        yp_4 = new ImageView(yellowPawn);

        bluePlayer = new Label();

        redPlayer = new Label();

        greenPlayer = new Label();


        roll1 = new Button("Roll");
        finish1 = new Button("Finish Turn");
        roll2 = new Button("Roll");
        finish2 = new Button("Finish Turn");
        roll3 = new Button("Roll");
        finish3 = new Button("Finish Turn");
        roll4 = new Button("Roll");
        finish4 = new Button("Finish Turn");

        die1 = new ImageView(Die.getEmpty());
        die2 = new ImageView(Die.getEmpty());
        die3 = new ImageView(Die.getEmpty());
        die4 = new ImageView(Die.getEmpty());

    }
    private void layoutNodes() {
        this.getChildren().add(board);

        //yellow player stuff
        VBox controls1 = new VBox(20);
        controls1.getChildren().addAll(roll1, die1);
        controls1.setAlignment(Pos.CENTER);
        roll1.setVisible(false);
        finish1.setVisible(false);
        die1.setVisible(false);
        this.getChildren().add(yellowPlayer);
        this.getChildren().add(controls1);
        this.getChildren().add(finish1);
        yellowPlayer.setTranslateX(500);
        yellowPlayer.setTranslateY(-250);
        controls1.setTranslateX(500);
        controls1.setTranslateY(-180);
        finish1.setTranslateX(500);
        finish1.setTranslateY(-210);

        Group yellow_pawns = new Group(yp_1, yp_2, yp_3, yp_4);
        this.getChildren().add(yellow_pawns);
        yp_1.setTranslateX(160);
        yp_1.setTranslateY(100);
        yp_1.setTranslateX(120);
        yp_1.setTranslateY(100);

        //player2 stuff
        VBox controls2 = new VBox(20);
        controls2.getChildren().addAll(roll2, die2);
        controls2.setAlignment(Pos.CENTER);
        roll2.setVisible(false);
        die2.setVisible(false);
        finish2.setVisible(false);
        this.getChildren().add(bluePlayer);
        this.getChildren().add(controls2);
        this.getChildren().add(finish2);
        bluePlayer.setTranslateX(-500);
        bluePlayer.setTranslateY(-250);
        controls2.setTranslateX(-500);
        controls2.setTranslateY(-180);
        finish2.setTranslateX(-500);
        finish2.setTranslateY(-210);

        //player3 stuff
        VBox controls3 = new VBox(20);
        controls3.getChildren().addAll(roll3, die3);
        controls3.setAlignment(Pos.CENTER);
        roll3.setVisible(false);
        die3.setVisible(false);
        finish3.setVisible(false);
        this.getChildren().add(redPlayer);
        this.getChildren().add(controls3);
        this.getChildren().add(finish3);
        redPlayer.setTranslateX(-500);
        redPlayer.setTranslateY(200);
        controls3.setTranslateX(-500);
        controls3.setTranslateY(270);
        finish3.setTranslateX(-500);
        finish3.setTranslateY(240);


        //player4 stuff
        VBox controls4 = new VBox(20);
        controls4.getChildren().addAll(roll4, die4);
        controls4.setAlignment(Pos.CENTER);
        roll4.setVisible(false);
        die4.setVisible(false);
        finish4.setVisible(false);
        this.getChildren().add(greenPlayer);
        this.getChildren().add(controls4);
        this.getChildren().add(finish4);
        greenPlayer.setTranslateX(500);
        greenPlayer.setTranslateY(200);
        controls4.setTranslateX(500);
        controls4.setTranslateY(270);
        finish4.setTranslateX(500);
        finish4.setTranslateY(240);


        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
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

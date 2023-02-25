package kdg.be.parchis.views.order;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.musicLogic.MainMusic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OrderView extends StackPane {
    private Image background;
    private Image board;
    private ImageView boardView;
    private Label player1Name;
    private Button roll1;
    private Label player2Name;
    private Button roll2;
    private Label player3Name;
    private Button roll3;
    private Label player4Name;
    private Button roll4;
    private Button back;
    private Button start;
    private ImageView diceFoto1;
    private ImageView diceFoto2;
    private ImageView diceFoto3;
    private ImageView diceFoto4;

    private int aiRoll2;

    private int aiRoll3;

    private int aiRoll4;

    public OrderView () throws FileNotFoundException, InterruptedException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        background = new Image(new FileInputStream("resources\\backgrounds\\ingame_background.png"));
        player1Name = new Label();
        player2Name = new Label();
        player3Name = new Label();
        player4Name = new Label();
        roll1 = new Button("Roll");
        roll2 = new Button("Roll");
        roll3 = new Button("Roll");
        roll4 = new Button("Roll");

        back = new Button("Back");
        start = new Button("START");
        board = new Image(new FileInputStream("resources\\graphics\\game\\board.png"));
        boardView = new ImageView(board);
        diceFoto1 = new ImageView(Die.getEmpty());
        diceFoto2 = new ImageView(Die.getEmpty());
        diceFoto3 = new ImageView(Die.getEmpty());
        diceFoto4 = new ImageView(Die.getEmpty());
    }
    private void layoutNodes() throws InterruptedException {
        this.getChildren().add(boardView);

        //player1 stuff
        VBox controls1 = new VBox(20);
        controls1.getChildren().addAll(roll1, diceFoto1);
        controls1.setAlignment(Pos.CENTER);
        this.getChildren().add(player1Name);
        this.getChildren().add(controls1);
        player1Name.setTranslateX(500);
        player1Name.setTranslateY(-250);
        controls1.setTranslateX(500);
        controls1.setTranslateY(-180);

        //player2 stuff
        VBox controls2 = new VBox(20);
        controls2.getChildren().addAll(roll2, diceFoto2);
        controls2.setAlignment(Pos.CENTER);
        this.getChildren().add(player2Name);
        this.getChildren().add(controls2);
        player2Name.setTranslateX(-500);
        player2Name.setTranslateY(-250);
        controls2.setTranslateX(-500);
        controls2.setTranslateY(-180);

        //player3 stuff
        VBox controls3 = new VBox(20);
        controls3.getChildren().addAll(roll3, diceFoto3);
        controls3.setAlignment(Pos.CENTER);
        this.getChildren().add(player3Name);
        this.getChildren().add(controls3);
        player3Name.setTranslateX(-500);
        player3Name.setTranslateY(200);
        controls3.setTranslateX(-500);
        controls3.setTranslateY(270);


        //player4 stuff
        VBox controls4 = new VBox(20);
        controls4.getChildren().addAll(roll4, diceFoto4);
        controls4.setAlignment(Pos.CENTER);
        this.getChildren().add(player4Name);
        this.getChildren().add(controls4);
        player4Name.setTranslateX(500);
        player4Name.setTranslateY(200);
        controls4.setTranslateX(500);
        controls4.setTranslateY(270);

        this.getChildren().add(back);
        this.getChildren().add(start);
        start.setPrefSize(150,150);
        start.setVisible(false);
        StackPane.setAlignment(back, Pos.BOTTOM_LEFT);
        StackPane.setMargin(back, new Insets(20));
        StackPane.setAlignment(start, Pos.CENTER);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        if(!MainMusic.getMediaPlayer().isMute()){
            MainMusic.stopMusic();
            MainMusic.playGameMusic();
        }
    }

    public Button getBack() {
        return back;
    }

    public Button getRoll1() {
        return roll1;
    }

    public ImageView getDiceFoto1() {
        return diceFoto1;
    }

    public ImageView getBoardView() {
        return boardView;
    }

    public Label getPlayer1Name() {
        return player1Name;
    }

    public Label getPlayer2Name() {
        return player2Name;
    }

    public Button getRoll2() {
        return roll2;
    }

    public Label getPlayer3Name() {
        return player3Name;
    }

    public Button getRoll3() {
        return roll3;
    }

    public Label getPlayer4Name() {
        return player4Name;
    }

    public Button getRoll4() {
        return roll4;
    }

    public ImageView getDiceFoto2() {
        return diceFoto2;
    }

    public ImageView getDiceFoto3() {
        return diceFoto3;
    }

    public ImageView getDiceFoto4() {
        return diceFoto4;
    }

    public int getAiRoll2() {
        return aiRoll2;
    }

    public int getAiRoll3() {
        return aiRoll3;
    }

    public int getAiRoll4() {
        return aiRoll4;
    }

    public Button getStart() {
        return start;
    }
}

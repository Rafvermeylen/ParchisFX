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
    private Image yellowPawnGlow;
    private ImageView yp_1;
    private ImageView yp_2;
    private ImageView yp_3;
    private ImageView yp_4;
    private Label bluePlayer;
    private Button roll2;
    private ImageView die2;
    private Button finish2;
    private Image bluePawn;
    private Image bluePawnGlow;
    private ImageView bp_1;
    private ImageView bp_2;
    private ImageView bp_3;
    private ImageView bp_4;
    private Label redPlayer;
    private Button roll3;
    private ImageView die3;
    private Button finish3;
    private Image redPawn;
    private Image redPawnGlow;
    private ImageView rp_1;
    private ImageView rp_2;
    private ImageView rp_3;
    private ImageView rp_4;
    private Label greenPlayer;
    private Button roll4;
    private ImageView die4;
    private Button finish4;
    private Image greenPawn;
    private Image greenPawnGlow;
    private ImageView gp_1;
    private ImageView gp_2;
    private ImageView gp_3;
    private ImageView gp_4;
    private Image boardImg;
    private Image background;
    private ImageView board;
    private Image glowNestYellow;
    private Image glowNestBlue;
    private Image glowNestRed;
    private Image glowNestGreen;
    private ImageView nestGlow;
    private Image status;
    private ImageView statusBar;
    private Label turns;

    public GameView () throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        boardImg = new Image(new FileInputStream("resources\\graphics\\game\\board.png"));
        background = new Image(new FileInputStream("resources\\backgrounds\\ingame_background.png"));
        board = new ImageView(boardImg);
        status = new Image(new FileInputStream("resources\\graphics\\game\\statusBar.png"));
        statusBar = new ImageView(status);

        turns = new Label();

        yellowPlayer = new Label();
        yellowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_yellow.png"));
        yellowPawnGlow = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_yellow_glow.png"));
        yp_1 = new ImageView(yellowPawn);
        yp_2 = new ImageView(yellowPawn);
        yp_3 = new ImageView(yellowPawn);
        yp_4 = new ImageView(yellowPawn);

        bluePlayer = new Label();
        bluePawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_blue.png"));
        bluePawnGlow = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_blue_glow.png"));
        bp_1 = new ImageView(bluePawn);
        bp_2 = new ImageView(bluePawn);
        bp_3 = new ImageView(bluePawn);
        bp_4 = new ImageView(bluePawn);

        redPlayer = new Label();
        redPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_red.png"));
        redPawnGlow = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_red_glow.png"));
        rp_1 = new ImageView(redPawn);
        rp_2 = new ImageView(redPawn);
        rp_3 = new ImageView(redPawn);
        rp_4 = new ImageView(redPawn);

        greenPlayer = new Label();
        greenPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_green.png"));
        greenPawnGlow = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_green_glow.png"));
        gp_1 = new ImageView(greenPawn);
        gp_2 = new ImageView(greenPawn);
        gp_3 = new ImageView(greenPawn);
        gp_4 = new ImageView(greenPawn);

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

        glowNestYellow = new Image(new FileInputStream("resources\\graphics\\game\\yellow_nest_glow.png"));
        glowNestBlue = new Image(new FileInputStream("resources\\graphics\\game\\blue_nest_glow.png"));
        glowNestRed = new Image(new FileInputStream("resources\\graphics\\game\\red_nest_glow.png"));
        glowNestGreen = new Image(new FileInputStream("resources\\graphics\\game\\green_nest_glow.png"));
        nestGlow = new ImageView();
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

        //yellow pawns
        //yp_1.setTranslateX(160);
        //yp_1.setTranslateY(-150);

        yp_1.setTranslateX(45);
        yp_1.setTranslateY(100);

        yp_2.setTranslateX(185);
        yp_2.setTranslateY(-150);
        yp_3.setTranslateX(210);
        yp_3.setTranslateY(-150);
        yp_4.setTranslateX(235);
        yp_4.setTranslateY(-150);

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

        //blue pawns
        bp_1.setTranslateX(-160);
        bp_1.setTranslateY(-150);
        bp_2.setTranslateX(-185);
        bp_2.setTranslateY(-150);
        bp_3.setTranslateX(-210);
        bp_3.setTranslateY(-150);
        bp_4.setTranslateX(-235);
        bp_4.setTranslateY(-150);

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

        //red pawns
        rp_1.setTranslateX(-160);
        rp_1.setTranslateY(180);
        rp_2.setTranslateX(-185);
        rp_2.setTranslateY(180);
        rp_3.setTranslateX(-210);
        rp_3.setTranslateY(180);
        rp_4.setTranslateX(-235);
        rp_4.setTranslateY(180);

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

        //green pawns
        gp_1.setTranslateX(160);
        gp_1.setTranslateY(180);
        gp_2.setTranslateX(185);
        gp_2.setTranslateY(180);
        gp_3.setTranslateX(210);
        gp_3.setTranslateY(180);
        gp_4.setTranslateX(235);
        gp_4.setTranslateY(180);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.getChildren().add(statusBar);
        setAlignment(statusBar, Pos.TOP_CENTER);
        this.getChildren().add(turns);
        setAlignment(turns, Pos.TOP_CENTER);


        this.getChildren().addAll(nestGlow,
                yp_1, yp_2, yp_3, yp_4,
                bp_1, bp_2, bp_3, bp_4,
                rp_1, rp_2, rp_3, rp_4,
                gp_1, gp_2, gp_3, gp_4);
        nestGlow.setVisible(false);
    }

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

    public Label getTurns() {
        return turns;
    }

    public Image getGlowNestYellow() {
        return glowNestYellow;
    }

    public ImageView getNestGlow() {
        return nestGlow;
    }

    public Image getGlowNestBlue() {
        return glowNestBlue;
    }

    public Image getGlowNestRed() {
        return glowNestRed;
    }

    public Image getGlowNestGreen() {
        return glowNestGreen;
    }

    public Image getYellowPawn() {
        return yellowPawn;
    }

    public ImageView getYp_1() {
        return yp_1;
    }

    public ImageView getYp_2() {
        return yp_2;
    }

    public ImageView getYp_3() {
        return yp_3;
    }

    public ImageView getYp_4() {
        return yp_4;
    }

    public Image getBluePawn() {
        return bluePawn;
    }

    public ImageView getBp_1() {
        return bp_1;
    }

    public ImageView getBp_2() {
        return bp_2;
    }

    public ImageView getBp_3() {
        return bp_3;
    }

    public ImageView getBp_4() {
        return bp_4;
    }

    public Image getRedPawn() {
        return redPawn;
    }

    public ImageView getRp_1() {
        return rp_1;
    }

    public ImageView getRp_2() {
        return rp_2;
    }

    public ImageView getRp_3() {
        return rp_3;
    }

    public ImageView getRp_4() {
        return rp_4;
    }

    public Image getGreenPawn() {
        return greenPawn;
    }

    public ImageView getGp_1() {
        return gp_1;
    }

    public ImageView getGp_2() {
        return gp_2;
    }

    public ImageView getGp_3() {
        return gp_3;
    }

    public ImageView getGp_4() {
        return gp_4;
    }

    public Image getYellowPawnGlow() {
        return yellowPawnGlow;
    }

    public Image getBluePawnGlow() {
        return bluePawnGlow;
    }

    public Image getRedPawnGlow() {
        return redPawnGlow;
    }

    public Image getGreenPawnGlow() {
        return greenPawnGlow;
    }
}

package kdg.be.parchis.views.order;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.menu.PlayerSetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OrderView extends StackPane {
    private PlayerSetup setup;
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
    private ImageView diceFoto1;
    private ImageView diceFoto2;
    private ImageView diceFoto3;
    private ImageView diceFoto4;




    public OrderView (PlayerSetup setup) throws FileNotFoundException {
        this.setup = setup;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        background = new Image(new FileInputStream("resources\\graphics\\game\\background_ingame.png"));
        player1Name = new Label(setup.getPlayers().get(0).getName());
        player2Name = new Label(setup.getPlayers().get(1).getName());
        player3Name = new Label(setup.getPlayers().get(2).getName());
        player4Name = new Label(setup.getPlayers().get(3).getName());
        roll1 = new Button("Roll");
        roll2 = new Button("Roll");
        roll3 = new Button("Roll");
        roll4 = new Button("Roll");

        back = new Button("Back");
        board = new Image(new FileInputStream("resources\\graphics\\game\\board.png"));
        boardView = new ImageView(board);
        diceFoto1 = new ImageView(Die.getEmpty());
        diceFoto2 = new ImageView(Die.getEmpty());
        diceFoto3 = new ImageView(Die.getEmpty());
        diceFoto4 = new ImageView(Die.getEmpty());

    }
    private void layoutNodes() {
        this.getChildren().add(boardView);

        //player1 stuff
        VBox controls1 = new VBox(20);
        controls1.getChildren().addAll(roll1, diceFoto1);
        controls1.setAlignment(Pos.CENTER);
        this.getChildren().add(player1Name);
        this.getChildren().add(controls1);
        player1Name.setTranslateX(350);
        player1Name.setTranslateY(-290);
        controls1.setTranslateX(350);
        controls1.setTranslateY(-220);

        //player2 stuff
        VBox controls2 = new VBox(20);
        controls2.getChildren().addAll(roll2, diceFoto2);
        controls2.setAlignment(Pos.CENTER);
        this.getChildren().add(player2Name);
        this.getChildren().add(controls2);
        player2Name.setTranslateX(-350);
        player2Name.setTranslateY(-290);
        controls2.setTranslateX(-350);
        controls2.setTranslateY(-220);
        if(setup.getAmountPlayers()<2){
            roll2.setVisible(false);
            //roll as cpu
            Die.throwDie();
            diceFoto2.setImage(Die.getDiceFoto().getImage());
        }

        //player3 stuff
        VBox controls3 = new VBox(20);
        controls3.getChildren().addAll(roll3, diceFoto3);
        controls3.setAlignment(Pos.CENTER);
        this.getChildren().add(player3Name);
        this.getChildren().add(controls3);
        player3Name.setTranslateX(-350);
        player3Name.setTranslateY(180);
        controls3.setTranslateX(-350);
        controls3.setTranslateY(250);
        if(setup.getAmountPlayers()<3){
            roll3.setVisible(false);
            //roll as cpu
            Die.throwDie();
            diceFoto3.setImage(Die.getDiceFoto().getImage());
        }

        //player4 stuff
        VBox controls4 = new VBox(20);
        controls4.getChildren().addAll(roll4, diceFoto4);
        controls4.setAlignment(Pos.CENTER);
        this.getChildren().add(player4Name);
        this.getChildren().add(controls4);
        player4Name.setTranslateX(350);
        player4Name.setTranslateY(180);
        controls4.setTranslateX(350);
        controls4.setTranslateY(250);
        if(setup.getAmountPlayers()<4){
            roll4.setVisible(false);
            //roll as cpu
            Die.throwDie();
            diceFoto4.setImage(Die.getDiceFoto().getImage());
        }

        this.getChildren().add(back);
        StackPane.setAlignment(back, Pos.BOTTOM_LEFT);
        StackPane.setMargin(back, new Insets(20));
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
}

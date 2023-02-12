package kdg.be.parchis.views.order;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kdg.be.parchis.model.menu.PlayerSetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OrderView extends BorderPane {
    private PlayerSetup setup;
    private Image background;
    private Label player1Name;
    private Label player2Name;
    private Label player3Name;
    private Label player4Name;
    private Button roll;
    private Button back;
    private Image empty;
    private Image die1;
    private Image die2;
    private Image die3;
    private Image die4;
    private Image die5;
    private Image die6;
    private ImageView diceFoto;



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
        roll = new Button("Roll");
        back = new Button("Back");

        empty = new Image(new FileInputStream("resources\\graphics\\die\\empty.png"));
        die1 = new Image(new FileInputStream("resources\\graphics\\die\\dice_one.png"));
        die2 = new Image(new FileInputStream("resources\\graphics\\die\\dice_two.png"));
        die3 = new Image(new FileInputStream("resources\\graphics\\die\\dice_three.png"));
        die4 = new Image(new FileInputStream("resources\\graphics\\die\\dice_four.png"));
        die5 = new Image(new FileInputStream("resources\\graphics\\die\\dice_five.png"));
        die6 = new Image(new FileInputStream("resources\\graphics\\die\\dice_six.png"));
        diceFoto = new ImageView(empty);
    }
    private void layoutNodes() {
        VBox names = new VBox(20);
        names.getChildren().addAll(player1Name, player2Name, player3Name, player4Name);
        names.setAlignment(Pos.CENTER);
        HBox layout = new HBox(30);
        layout.getChildren().addAll(names, roll,diceFoto);
        layout.setAlignment(Pos.CENTER);
        this.setCenter(layout);
        this.setBottom(back);
        BorderPane.setMargin(diceFoto, new Insets(30));
        BorderPane.setAlignment(layout, Pos.CENTER);
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));
    }

    public Button getBack() {
        return back;
    }

    public Button getRoll() {
        return roll;
    }

    public Image getDie1() {
        return die1;
    }

    public Image getDie2() {
        return die2;
    }

    public Image getDie3() {
        return die3;
    }

    public Image getDie4() {
        return die4;
    }

    public Image getDie5() {
        return die5;
    }

    public Image getDie6() {
        return die6;
    }

    public ImageView getDiceFoto() {
        return diceFoto;
    }
}

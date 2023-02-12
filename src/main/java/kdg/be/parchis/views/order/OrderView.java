package kdg.be.parchis.views.order;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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


    public OrderView (PlayerSetup setup) throws FileNotFoundException {
        this.setup = setup;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() throws FileNotFoundException {
        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));
        player1Name = new Label(setup.getPlayers().get(0).getName());
        player2Name = new Label(setup.getPlayers().get(1).getName());
        player3Name = new Label(setup.getPlayers().get(2).getName());
        player4Name = new Label(setup.getPlayers().get(3).getName());
        roll = new Button("Roll");
    }
    private void layoutNodes() {
        VBox names = new VBox(20);
        names.getChildren().addAll(player1Name, player2Name, player3Name, player4Name);
        names.setAlignment(Pos.CENTER);
        HBox layout = new HBox(30);
        layout.getChildren().addAll(names, roll);
        layout.setAlignment(Pos.CENTER);
        this.setCenter(layout);
        BorderPane.setAlignment(layout, Pos.CENTER);
        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

}

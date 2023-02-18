package kdg.be.parchis.views.playerselect;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerSelectView extends BorderPane {
    private Button onePlayer;
    private Image onePlayerPic;
    private Button twoPlayers;
    private Image twoPlayerPic;

    private Button threePlayers;
    private Image threePlayerPic;

    private Button fourPlayers;
    private Image fourPlayerPic;

    private Image background;
    private Button back;

    public PlayerSelectView() throws FileNotFoundException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        back = new Button("Back");
        onePlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\one_player.png"));
        twoPlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\two_player.png"));
        threePlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\three_player.png"));
        fourPlayerPic = new Image(new FileInputStream("resources\\graphics\\buttons\\amountPlayers\\four_player.png"));
        onePlayer = new Button("", new ImageView(onePlayerPic));
        twoPlayers = new Button("", new ImageView(twoPlayerPic));
        threePlayers = new Button("", new ImageView(threePlayerPic));
        fourPlayers = new Button("", new ImageView(fourPlayerPic));
        background = new Image(new FileInputStream("resources\\backgrounds\\Background.png"));

    }

    private void layoutNodes() {
        HBox hBox = new HBox(20);
        onePlayer.setPrefSize(100, 100);
        twoPlayers.setPrefSize(100, 100);
        threePlayers.setPrefSize(100, 100);
        fourPlayers.setPrefSize(100, 100);

        hBox.getChildren().addAll(onePlayer, twoPlayers, threePlayers, fourPlayers);
        hBox.setAlignment(Pos.CENTER);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.setCenter(hBox);
        this.setBottom(back);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setAlignment(back, Pos.CENTER_LEFT);
        BorderPane.setMargin(back, new Insets(20));
    }

    public Button getOnePlayer() {
        return onePlayer;
    }

    public Button getTwoPlayers() {
        return twoPlayers;
    }

    public Button getThreePlayers() {
        return threePlayers;
    }

    public Button getFourPlayers() {
        return fourPlayers;
    }

    public Button getBack() {
        return back;
    }
}

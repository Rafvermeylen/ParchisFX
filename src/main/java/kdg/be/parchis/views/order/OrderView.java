package kdg.be.parchis.views.order;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kdg.be.parchis.model.musiclogic.Music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class OrderView extends StackPane {
    private List<Button> rollButtons;
    private List<ImageView> dieView;
    private List<Image> dieFaces;
    private Image background;
    private ImageView boardView;
    private Label player1Name;
    private Label player2Name;
    private Label player3Name;
    private Label player4Name;
    private Button back;
    private Button start;

    public OrderView() throws FileNotFoundException, InterruptedException {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() throws FileNotFoundException {
        background = new Image(new FileInputStream("resources\\backgrounds\\ingame_background.png"));

        rollButtons = Arrays.asList(
                new Button("Roll"),
                new Button("Roll"),
                new Button("Roll"),
                new Button("Roll")
        );

        player1Name = new Label();
        player2Name = new Label();
        player3Name = new Label();
        player4Name = new Label();


        back = new Button("Back");
        start = new Button("START");
        Image board = new Image(new FileInputStream("resources\\graphics\\game\\board.png"));
        boardView = new ImageView(board);

        dieView = Arrays.asList(
                new ImageView(),
                new ImageView(),
                new ImageView(),
                new ImageView()
        );

        dieFaces = Arrays.asList(
                new Image(new FileInputStream("resources/graphics/die/empty.png")),
                new Image(new FileInputStream("resources/graphics/die/1.png")),
                new Image(new FileInputStream("resources/graphics/die/2.png")),
                new Image(new FileInputStream("resources/graphics/die/3.png")),
                new Image(new FileInputStream("resources/graphics/die/4.png")),
                new Image(new FileInputStream("resources/graphics/die/5.png")),
                new Image(new FileInputStream("resources/graphics/die/6.png"))
        );
    }

    private void layoutNodes() {
        this.getChildren().add(boardView);

        //player1 stuff
        VBox controls1 = new VBox(20);
        controls1.getChildren().addAll(rollButtons.get(0), dieView.get(0));
        controls1.setAlignment(Pos.CENTER);
        this.getChildren().add(player1Name);
        this.getChildren().add(controls1);
        player1Name.setTranslateX(500);
        player1Name.setTranslateY(-250);
        controls1.setTranslateX(500);
        controls1.setTranslateY(-180);

        //player2 stuff
        VBox controls2 = new VBox(20);
        controls2.getChildren().addAll(rollButtons.get(1), dieView.get(1));
        controls2.setAlignment(Pos.CENTER);
        this.getChildren().add(player2Name);
        this.getChildren().add(controls2);
        player2Name.setTranslateX(-500);
        player2Name.setTranslateY(-250);
        controls2.setTranslateX(-500);
        controls2.setTranslateY(-180);

        //player3 stuff
        VBox controls3 = new VBox(20);
        controls3.getChildren().addAll(rollButtons.get(2), dieView.get(2));
        controls3.setAlignment(Pos.CENTER);
        this.getChildren().add(player3Name);
        this.getChildren().add(controls3);
        player3Name.setTranslateX(-500);
        player3Name.setTranslateY(200);
        controls3.setTranslateX(-500);
        controls3.setTranslateY(270);


        //player4 stuff
        VBox controls4 = new VBox(20);
        controls4.getChildren().addAll(rollButtons.get(3), dieView.get(3));
        controls4.setAlignment(Pos.CENTER);
        this.getChildren().add(player4Name);
        this.getChildren().add(controls4);
        player4Name.setTranslateX(500);
        player4Name.setTranslateY(200);
        controls4.setTranslateX(500);
        controls4.setTranslateY(270);

        this.getChildren().addAll(back, start);
        start.setPrefSize(150, 150);
        start.setVisible(false);
        StackPane.setAlignment(back, Pos.BOTTOM_LEFT);
        StackPane.setMargin(back, new Insets(20));
        StackPane.setAlignment(start, Pos.CENTER);

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        if (!Music.getMediaPlayer().isMute()) {
            Music.stopMusic();
            Music.playGameMusic();
        }
    }

    public Button getBack() {
        return back;
    }

    public Label getPlayer1Name() {
        return player1Name;
    }

    public Label getPlayer2Name() {
        return player2Name;
    }

    public Label getPlayer3Name() {
        return player3Name;
    }

    public Label getPlayer4Name() {
        return player4Name;
    }

    public Button getStart() {
        return start;
    }

    public Image getDieFace(int thrown){
        return dieFaces.get(thrown);
    }

    public Button getRollButtons(int index) {
        return rollButtons.get(index);
    }

    public ImageView getDieView(int index) {
        return dieView.get(index);
    }
}

package kdg.be.parchis.views.game;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kdg.be.parchis.model.game.Die;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameView extends StackPane {
    private List<ImageView> pawns;
    private List<ImageView> dice;
    private List<Button> finish;
    private List<Button> rolls;
    private List<Label> playerNames;
    private List<Image> glowingPawns;
    private Image yellowPawn;

    private Image yellowGlowPawn;

    //private Label bluePlayer;
    private Image bluePawn;
    //private Label redPlayer;

    private Image blueGlowPawn;

    private Image redPawn;
    //private Label greenPlayer;

    private Image redGlowPawn;

    private Image greenPawn;

    private Image greenGlowPawn;

    private Image boardImg;
    private Image background;
    private ImageView board;
    private ImageView nestGlow;
    private Image status;
    private ImageView statusBar;
    private Label turns;

    public GameView() throws FileNotFoundException {
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

        //yellowPlayer = new Label();
        yellowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_yellow.png"));
        yellowGlowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_yellow_glow.png"));


        //bluePlayer = new Label();
        bluePawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_blue.png"));
        blueGlowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_blue_glow.png"));


        //redPlayer = new Label();
        redPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_red.png"));
        redGlowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_red_glow.png"));


        //greenPlayer = new Label();
        greenPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_green.png"));
        greenGlowPawn = new Image(new FileInputStream("resources\\graphics\\game\\pawn\\pawn_green_glow.png"));


        pawns = Arrays.asList(
                new ImageView(yellowPawn),
                new ImageView(yellowPawn),
                new ImageView(yellowPawn),
                new ImageView(yellowPawn),
                new ImageView(bluePawn),
                new ImageView(bluePawn),
                new ImageView(bluePawn),
                new ImageView(bluePawn),
                new ImageView(redPawn),
                new ImageView(redPawn),
                new ImageView(redPawn),
                new ImageView(redPawn),
                new ImageView(greenPawn),
                new ImageView(greenPawn),
                new ImageView(greenPawn),
                new ImageView(greenPawn)
        );

        dice = Arrays.asList(
                new ImageView(Die.getEmpty()),
                new ImageView(Die.getEmpty()),
                new ImageView(Die.getEmpty()),
                new ImageView(Die.getEmpty())
        );

        finish = Arrays.asList(
                new Button("Finish Turn"),
                new Button("Finish Turn"),
                new Button("Finish Turn"),
                new Button("Finish Turn")
        );

        rolls = Arrays.asList(
                new Button("Roll"),
                new Button("Roll"),
                new Button("Roll"),
                new Button("Roll")
        );

        playerNames = Arrays.asList(
                new Label(""),
                new Label(""),
                new Label(""),
                new Label("")
        );

        glowingPawns = Arrays.asList(
            yellowGlowPawn,
            blueGlowPawn,
            redGlowPawn,
            greenGlowPawn
        );


        nestGlow = new ImageView();
    }

    private void layoutNodes() {
        this.getChildren().add(board);
        VBox box;
        for (int i = 0; i < 4; i++) {
            box= new VBox(20);
            box.getChildren().addAll(getRoll(i), getDie(i));
            box.setAlignment(Pos.CENTER);
            getRoll(i).setVisible(false);
            getFinish(i).setVisible(false);
            getDie(i).setVisible(false);
            this.getChildren().add(getPlayerName(i));
            this.getChildren().add(box);
            this.getChildren().add(getFinish(i));
            switch (i) {
                case 0 -> {

                    getPlayerName(i).setTranslateX(500);
                    getPlayerName(i).setTranslateY(-250);
                    box.setTranslateX(500);
                    box.setTranslateY(-180);
                    getFinish(i).setTranslateX(500);
                    getFinish(i).setTranslateY(-210);
                }
                case 1 -> {
                    getPlayerName(i).setTranslateX(-500);
                    getPlayerName(i).setTranslateY(-250);
                    box.setTranslateX(-500);
                    box.setTranslateY(-180);
                    getFinish(i).setTranslateX(-500);
                    getFinish(i).setTranslateY(-210);
                }
                case 2 -> {
                    getPlayerName(i).setTranslateX(-500);
                    getPlayerName(i).setTranslateY(200);
                    box.setTranslateX(-500);
                    box.setTranslateY(270);
                    getFinish(i).setTranslateX(-500);
                    getFinish(i).setTranslateY(240);
                }
                case 3 -> {
                    getPlayerName(i).setTranslateX(500);
                    getPlayerName(i).setTranslateY(200);
                    box.setTranslateX(500);
                    box.setTranslateY(270);
                    getFinish(i).setTranslateX(500);
                    getFinish(i).setTranslateY(240);
                }
            }
        }

        //yellow pawns
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0 -> getPawn(i).setTranslateX(160);
                case 1 -> getPawn(i).setTranslateX(185);
                case 2 -> getPawn(i).setTranslateX(210);
                case 3 -> getPawn(i).setTranslateX(235);
            }
            getPawn(i).setTranslateY(-150);
        }


        //Blue pawns
        for (int i = 4; i<8; i++) {
            switch (i){
                case 4 -> getPawn(i).setTranslateX(-160);
                case 5 -> getPawn(i).setTranslateX(-185);
                case 6 -> getPawn(i).setTranslateX(-210);
                case 7 -> getPawn(i).setTranslateX(-235);
            }
            getPawn(i).setTranslateY(-150);
        }

        //Red pawns
        for (int i = 8; i<12; i++) {
            switch (i){
                case 8 -> getPawn(i).setTranslateX(-160);
                case 9 -> getPawn(i).setTranslateX(-185);
                case 10 -> getPawn(i).setTranslateX(-210);
                case 11 -> getPawn(i).setTranslateX(-235);
            }
            getPawn(i).setTranslateY(180);
        }

        //Green pawns
        for (int i = 12; i<16; i++) {
            switch (i){
                case 12 -> getPawn(i).setTranslateX(160);
                case 13 -> getPawn(i).setTranslateX(185);
                case 14 -> getPawn(i).setTranslateX(210);
                case 15 -> getPawn(i).setTranslateX(235);
            }
            getPawn(i).setTranslateY(180);
        }

        this.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.getChildren().add(statusBar);
        setAlignment(statusBar, Pos.TOP_CENTER);
        this.getChildren().add(turns);
        setAlignment(turns, Pos.TOP_CENTER);


        this.getChildren().addAll(nestGlow);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.getChildren().add(getPawn(j + (4 * i)));
            }
        }
        nestGlow.setVisible(false);
    }

    public Label getTurns() {
        return turns;
    }

    public ImageView getNestGlow() {
        return nestGlow;
    }

    public ImageView getPawn(int i) {
        return pawns.get(i);
    }

    public List<ImageView> getPawn() {
        return pawns;
    }

    public ImageView getDie(int i) {
        return dice.get(i);
    }

    public Button getFinish(int i) {
        return finish.get(i);
    }

    public List<Button> getFinish() {
        return finish;
    }

    public Button getRoll(int i) {
        return rolls.get(i);
    }

    public List<Button> getRoll() {
        return rolls;
    }

    public Label getPlayerName(int i) {
        return playerNames.get(i);
    }

    public List<Label> getPlayerName() {
        return playerNames;
    }


    public void rearrangePawns() {
        // Create a Comparator that compares Nodes by their Y coordinate
        Comparator<Node> nodeYComparator = Comparator.comparingDouble(node -> {
            return node.getBoundsInParent().getMinY();
        });

        // Get all the ImageViews, Labels and Buttons from the StackPane's children, except for the nest glow
        List<Node> pawnsIVs = this.getChildren().stream()
                .filter(node -> node instanceof ImageView || node instanceof Label || node instanceof Button)
                .filter(node -> node != nestGlow)
                .collect(Collectors.toList());

        // Sort the Nodes by their Y coordinate
        pawnsIVs.sort(nodeYComparator);

        // Remove all the ImageViews and Labels from the StackPane
        this.getChildren().removeAll(pawnsIVs);

        // Add the ImageViews and Labels back to the StackPane in the correct order
        this.getChildren().addAll(pawnsIVs);
        board.toBack();
    }

    public void removeGlow(ImageView p, String color) {
        switch (color) {
            case "yellow" -> p.setImage(yellowPawn);
            case "blue" -> p.setImage(bluePawn);
            case "red" -> p.setImage(redPawn);
            case "green" -> p.setImage(greenPawn);
        }
    }

    public void addGlow(ImageView p, String color) {
        switch (color) {
            case "yellow" -> p.setImage(yellowGlowPawn);
            case "blue" -> p.setImage(blueGlowPawn);
            case "red" -> p.setImage(redGlowPawn);
            case "green" -> p.setImage(greenGlowPawn);
        }
    }

    public void addNestGlow(String color) {
        try {
            nestGlow.setImage(new Image(new FileInputStream("resources\\graphics\\game\\" + color + "_nest_glow.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasGlow(ImageView im) {
        return glowingPawns.contains(im.getImage());
    }

    public void removeAllGlow() {
        for (int i = 0; i < 16; i++) {
            switch (i / 4) {
                case 0 -> pawns.get(i).setImage(yellowPawn);
                case 1 -> pawns.get(i).setImage(bluePawn);
                case 2 -> pawns.get(i).setImage(redPawn);
                case 3 -> pawns.get(i).setImage(greenPawn);
            }
        }
    }
}

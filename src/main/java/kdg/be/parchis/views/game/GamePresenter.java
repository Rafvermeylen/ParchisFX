package kdg.be.parchis.views.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kdg.be.parchis.model.game.Colors;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.game.Player;
import kdg.be.parchis.model.musicLogic.SoundClass;

import static java.lang.Thread.sleep;

public class GamePresenter {
    private Game gameSession;
    private GameView view;
    private final KeyCode fullscreenKey = KeyCode.F;


    public GamePresenter(
            Game model,
            GameView view) {
        this.gameSession = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getRoll1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll1().setVisible(false);
                SoundClass.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows()==3){
                    //return last pawn...

                    view.getFinish1().setVisible(true);
                    return;
                }

                view.getDie1().setVisible(true);
                view.getDie1().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish1().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getYellowPlayer())){
                    //automatically get pawn out of nest if there isn't anything else to do
                    //gameSession.yellowLeaveNest();
                    view.getNestGlow().setImage(view.getGlowNestYellow());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() != 5){
                    //move pawn automatically if you didn't throw a 5
                    //still needs selection incase you can choose which pawn to move.
                    //...
                    gameSession.movePawn(gameSession.getYellowPlayer());
                    view.getFinish1().setVisible(true);

                } else {
                    //choose to move or move out of nest
                    //...
                    view.getFinish1().setVisible(true);
                }

                if (Die.getThrown()==6){
                    view.getRoll1().setVisible(true);
                    view.getFinish1().setVisible(false);
                }
            }
        });


        view.getRoll2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll2().setVisible(false);
                SoundClass.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows()==3){
                    //return last pawn...

                    view.getFinish2().setVisible(true);
                    return;
                }
                view.getDie2().setVisible(true);
                view.getDie2().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish2().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getBluePlayer())){
                    //automatically get pawn out of nest if there isn't anything else to do
                    //gameSession.yellowLeaveNest();
                    view.getNestGlow().setImage(view.getGlowNestBlue());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() != 5){
                    //move pawn automatically if you didn't throw a 5
                    //still needs selection incase you can choose which pawn to move.
                    //...
                    gameSession.movePawn(gameSession.getBluePlayer());
                    view.getFinish2().setVisible(true);
                } else {
                    //choose to move or move out of nest
                    //...
                    view.getFinish2().setVisible(true);
                }

                if (Die.getThrown()==6){
                    view.getRoll2().setVisible(true);
                    view.getFinish2().setVisible(false);
                }
            }
        });
        view.getRoll3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll3().setVisible(false);
                SoundClass.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows()==3){
                    //return last pawn...

                    view.getFinish3().setVisible(true);
                    return;
                }
                view.getDie3().setVisible(true);
                view.getDie3().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish3().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getRedPlayer())){
                    //automatically get pawn out of nest if there isn't anything else to do
                    //gameSession.yellowLeaveNest();
                    view.getNestGlow().setImage(view.getGlowNestRed());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() != 5){
                    //move pawn automatically if you didn't throw a 5
                    //still needs selection incase you can choose which pawn to move.
                    //...
                    gameSession.movePawn(gameSession.getRedPlayer());
                    view.getFinish3().setVisible(true);
                } else {
                    //choose to move or move out of nest
                    //...
                    view.getFinish3().setVisible(true);
                }

                if (Die.getThrown()==6){
                    view.getRoll3().setVisible(true);
                    view.getFinish3().setVisible(false);
                }
            }
        });
        view.getRoll4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll4().setVisible(false);
                SoundClass.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows()==3){
                    //return last pawn...

                    view.getFinish4().setVisible(true);
                    return;
                }
                view.getDie4().setVisible(true);
                view.getDie4().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish4().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getGreenPlayer())){
                    //automatically get pawn out of nest if there isn't anything else to do
                    //gameSession.yellowLeaveNest();
                    view.getNestGlow().setImage(view.getGlowNestGreen());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() != 5){
                    //move pawn automatically if you didn't throw a 5
                    //still needs selection incase you can choose which pawn to move.
                    //...
                    gameSession.movePawn(gameSession.getGreenPlayer());
                    view.getFinish4().setVisible(true);
                } else {
                    //choose to move or move out of nest
                    //...
                    view.getFinish4().setVisible(true);
                }

                if (Die.getThrown()==6){
                    view.getRoll4().setVisible(true);
                    view.getFinish4().setVisible(false);
                }
            }
        });

        view.getFinish1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getFinish1().setVisible(false);
                view.getDie1().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
                    view.getRoll3().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
                    view.getRoll4().setVisible(true);
                }
            }
        });

        view.getFinish2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getFinish2().setVisible(false);
                view.getDie2().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
                    view.getRoll3().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
                    view.getRoll4().setVisible(true);
                }
            }
        });

        view.getFinish3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getFinish3().setVisible(false);
                view.getDie3().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
                    view.getRoll4().setVisible(true);
                }
            }
        });

        view.getFinish4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getFinish4().setVisible(false);
                view.getDie4().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
                    view.getRoll3().setVisible(true);
                }
            }
        });

        view.getNestGlow().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getNestGlow().getImage().equals(view.getGlowNestYellow())){
                    gameSession.yellowLeaveNest();
                    view.getFinish1().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestBlue())){
                    gameSession.blueLeaveNest();
                    view.getFinish2().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestRed())){
                    gameSession.redLeaveNest();
                    view.getFinish3().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestGreen())){
                    gameSession.greenLeaveNest();
                    view.getFinish4().setVisible(true);
                }
                view.getNestGlow().setVisible(false);
            }
        });


    }

    private void updateView() {
        view.getTurns().setText("turn: " + gameSession.getTurn());
        //set names
        for (Player p : gameSession.getPlayers()) {
            if (p.getColor().equals(Colors.YELLOW)) {
                view.getYellowPlayer().setText(p.getName());
            } else if (p.getColor().equals(Colors.BLUE)) {
                view.getBluePlayer().setText(p.getName());
            } else if (p.getColor().equals(Colors.RED)) {
                view.getRedPlayer().setText(p.getName());
            } else if (p.getColor().equals(Colors.GREEN)) {
                view.getGreenPlayer().setText(p.getName());
            }
        }
        //View controlls for first rolling player
        if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
            view.getRoll1().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
            view.getRoll2().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
            view.getRoll3().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
            view.getRoll4().setVisible(true);
        }
    }

    public void addWindowEventHandlers() {
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == fullscreenKey) {
                Scene scene = view.getScene();
                if (scene != null) {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setFullScreen(!stage.isFullScreen());
                }
            }
        });
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}

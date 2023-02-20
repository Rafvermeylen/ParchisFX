package kdg.be.parchis.views.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gameSession.roll();
                view.getDie1().setVisible(true);
                view.getDie1().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getYellowPlayer())) {
                    view.getFinish1().setVisible(true);
                }
            }
        });
        view.getRoll2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll2().setVisible(false);
                SoundClass.playRoll();
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gameSession.roll();
                view.getDie2().setVisible(true);
                view.getDie2().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getBluePlayer())) {
                    view.getFinish2().setVisible(true);
                }
            }
        });
        view.getRoll3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll3().setVisible(false);
                SoundClass.playRoll();
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gameSession.roll();
                view.getDie3().setVisible(true);
                view.getDie3().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getRedPlayer())) {
                    view.getFinish3().setVisible(true);
                }
            }
        });
        view.getRoll4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll4().setVisible(false);
                SoundClass.playRoll();
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gameSession.roll();
                view.getDie4().setVisible(true);
                view.getDie4().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getGreenPlayer())) {
                    view.getFinish4().setVisible(true);
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

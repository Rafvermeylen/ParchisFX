package kdg.be.parchis.views.order;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.game.GamePresenter;
import kdg.be.parchis.views.game.GameView;
import kdg.be.parchis.views.playersetup.PlayerSetupView;

import java.io.FileNotFoundException;

public class OrderPresenter {
    private final PlayerSetup setup;
    private final OrderView view;
    private final PlayerSetupView backView;

    public OrderPresenter(
            PlayerSetup model,
            OrderView view,
            PlayerSetupView backView) {
        this.setup = model;
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                view.getScene().setRoot(backView);
                if (!Music.getMediaPlayer().isMute()) {
                    Music.stopMusic();
                    Music.playMenuMusic();
                }
            }
        });

        view.getRoll1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll1().setVisible(false);
                Sound.playRoll();
                view.getRoll1().setDisable(true);
                view.getRoll1().setDisable(false);
                Die.throwDie();
                setup.addRoll(0, Die.getThrown());
                view.getDiceFoto1().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()) {
                    setup.order();
                    view.getStart().setVisible(true);
                }
            }
        });

        view.getRoll2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll2().setVisible(false);
                Sound.playRoll();
                view.getRoll2().setDisable(true);
                view.getRoll2().setDisable(false);
                Die.throwDie();
                setup.addRoll(1, Die.getThrown());
                view.getDiceFoto2().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()) {
                    setup.order();
                    view.getStart().setVisible(true);
                }
            }
        });

        view.getRoll3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll3().setVisible(false);
                Sound.playRoll();
                view.getRoll3().setDisable(true);
                view.getRoll3().setDisable(false);
                Die.throwDie();
                setup.addRoll(2, Die.getThrown());
                view.getDiceFoto3().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()) {
                    setup.order();
                    view.getStart().setVisible(true);
                }
            }
        });

        view.getRoll4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll4().setVisible(false);
                Sound.playRoll();
                view.getRoll4().setDisable(true);
                view.getRoll4().setDisable(false);
                Die.throwDie();
                setup.addRoll(3, Die.getThrown());
                view.getDiceFoto4().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()) {
                    setup.order();
                    view.getStart().setVisible(true);
                }
            }
        });

        view.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                Game gameSession = new Game(setup.getPlayers());
                GameView gameView = null;
                try {
                    gameView = new GameView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                view.getScene().setRoot(gameView);
            }
        });
    }

    private void updateView() {
        view.getPlayer1Name().setText(setup.getPlayers().get(0).getName());
        view.getPlayer2Name().setText(setup.getPlayers().get(1).getName());
        view.getPlayer3Name().setText(setup.getPlayers().get(2).getName());
        view.getPlayer4Name().setText(setup.getPlayers().get(3).getName());

        if (setup.getAmountPlayers() < 2) {
            view.getRoll2().setVisible(false);
            //roll as cpu
            Die.throwDie();
            view.getDiceFoto2().setImage(Die.getDiceFoto().getImage());
            setup.addRoll(1, Die.getThrown());
        }
        if (setup.getAmountPlayers() < 3) {
            view.getRoll3().setVisible(false);
            //roll as cpu
            Die.throwDie();
            view.getDiceFoto3().setImage(Die.getDiceFoto().getImage());
            setup.addRoll(2, Die.getThrown());
        }
        if (setup.getAmountPlayers() < 4) {
            view.getRoll4().setVisible(false);
            //roll as cpu
            Die.throwDie();
            view.getDiceFoto4().setImage(Die.getDiceFoto().getImage());
            setup.addRoll(3, Die.getThrown());
        }
    }
}
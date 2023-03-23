package kdg.be.parchis.views.order;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.game.GamePresenter;
import kdg.be.parchis.views.game.GameView;
import kdg.be.parchis.views.playersetup.PlayerSetupView;
import java.io.FileNotFoundException;

public class OrderPresenter {
    private final PlayerSetup SETUP;
    private final OrderView VIEW;
    private final PlayerSetupView BACKVIEW;
    public OrderPresenter(
            PlayerSetup model,
            OrderView view,
            PlayerSetupView backView) {
        this.SETUP = model;
        this.VIEW = view;
        this.BACKVIEW = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        VIEW.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                VIEW.getScene().setRoot(BACKVIEW);
                if (!Music.getMediaPlayer().isMute()) {
                    Music.stopMusic();
                    Music.playMenuMusic();
                }
            }
        });

        VIEW.getRoll1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VIEW.getRoll1().setVisible(false);
                Sound.playRoll();
                VIEW.getRoll1().setDisable(true);
                VIEW.getRoll1().setDisable(false);
                SETUP.roll();
                SETUP.addRoll(0, SETUP.getThrown());
                VIEW.getDicePhoto1().setImage(VIEW.getDieFace(SETUP.getThrown()));
                VIEW.getBack().setVisible(false);
                if (SETUP.didPlayersRoll()) {
                    SETUP.order();
                    VIEW.getStart().setVisible(true);
                }
            }
        });

        VIEW.getRoll2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VIEW.getRoll2().setVisible(false);
                Sound.playRoll();
                VIEW.getRoll2().setDisable(true);
                VIEW.getRoll2().setDisable(false);
                SETUP.roll();
                SETUP.addRoll(1, SETUP.getThrown());
                VIEW.getDicePhoto2().setImage(VIEW.getDieFace(SETUP.getThrown()));
                VIEW.getBack().setVisible(false);
                if (SETUP.didPlayersRoll()) {
                    SETUP.order();
                    VIEW.getStart().setVisible(true);
                }
            }
        });

        VIEW.getRoll3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VIEW.getRoll3().setVisible(false);
                Sound.playRoll();
                VIEW.getRoll3().setDisable(true);
                VIEW.getRoll3().setDisable(false);
                SETUP.roll();
                SETUP.addRoll(2, SETUP.getThrown());
                VIEW.getDicePhoto3().setImage(VIEW.getDieFace(SETUP.getThrown()));
                VIEW.getBack().setVisible(false);
                if (SETUP.didPlayersRoll()) {
                    SETUP.order();
                    VIEW.getStart().setVisible(true);
                }
            }
        });

        VIEW.getRoll4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VIEW.getRoll4().setVisible(false);
                Sound.playRoll();
                VIEW.getRoll4().setDisable(true);
                VIEW.getRoll4().setDisable(false);
                SETUP.roll();
                SETUP.addRoll(3, SETUP.getThrown());
                VIEW.getDicePhoto4().setImage(VIEW.getDieFace(SETUP.getThrown()));
                VIEW.getBack().setVisible(false);
                if (SETUP.didPlayersRoll()) {
                    SETUP.order();
                    VIEW.getStart().setVisible(true);
                }
            }
        });

        VIEW.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                Game gameSession = new Game(SETUP.getPlayers());
                GameView gameView = null;
                try {
                    gameView = new GameView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                VIEW.getScene().setRoot(gameView);
            }
        });
    }

    private void updateView() {
        VIEW.getPlayer1Name().setText(SETUP.getPlayers().get(0).getName());
        VIEW.getPlayer2Name().setText(SETUP.getPlayers().get(1).getName());
        VIEW.getPlayer3Name().setText(SETUP.getPlayers().get(2).getName());
        VIEW.getPlayer4Name().setText(SETUP.getPlayers().get(3).getName());

        if (SETUP.getAmountPlayers() < 2) {
            VIEW.getRoll2().setVisible(false);
            //roll as cpu
            SETUP.roll();
            VIEW.getDicePhoto2().setImage(VIEW.getDieFace(SETUP.getThrown()));
            SETUP.addRoll(1, SETUP.getThrown());
        }
        if (SETUP.getAmountPlayers() < 3) {
            VIEW.getRoll3().setVisible(false);
            //roll as cpu
            SETUP.roll();
            VIEW.getDicePhoto3().setImage(VIEW.getDieFace(SETUP.getThrown()));
            SETUP.addRoll(2, SETUP.getThrown());
        }
        if (SETUP.getAmountPlayers() < 4) {
            VIEW.getRoll4().setVisible(false);
            //roll as cpu
            SETUP.roll();
            VIEW.getDicePhoto4().setImage(VIEW.getDieFace(SETUP.getThrown()));
            SETUP.addRoll(3, SETUP.getThrown());
        }
    }
}
package kdg.be.parchis.views.order;

import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.views.game.GamePresenter;
import kdg.be.parchis.views.game.GameView;
import kdg.be.parchis.model.musiclogic.Music;
import kdg.be.parchis.model.musiclogic.Sound;
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
        VIEW.getBack().setOnAction(actionEvent -> {
            Sound.playClick();
            VIEW.getScene().setRoot(BACKVIEW);
            if (!Music.getMediaPlayer().isMute()) {
                Music.stopMusic();
                Music.playMenuMusic();
            }
        });

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            VIEW.getRollButtons(finalI).setOnAction(actionEvent -> {
                VIEW.getRollButtons(finalI).setVisible(false);
                Sound.playRoll();
                SETUP.roll();
                SETUP.addRoll(finalI, SETUP.getThrown());
                VIEW.getDieView(finalI).setImage(VIEW.getDieFace(SETUP.getThrown()));
                VIEW.getBack().setVisible(false);
                if (SETUP.didPlayersRoll()) {
                    SETUP.order();
                    VIEW.getStart().setVisible(true);
                }
            });
        }


        VIEW.getStart().setOnAction(actionEvent -> {
            Sound.playClick();
            Game gameSession = new Game(SETUP.getPlayers());
            GameView gameView;
            try {
                gameView = new GameView();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
            VIEW.getScene().setRoot(gameView);
        });
    }

    private void updateView() {
        for (int i = 0; i < 4; i++) {
            VIEW.getPlayerName(i).setText(SETUP.getPlayers().get(i).getName());
        }

        if (SETUP.getAmountPlayers() < 2) {
            VIEW.getRollButtons(1).setVisible(false);
            //roll as cpu
            SETUP.roll();
            VIEW.getDieView(1).setImage(VIEW.getDieFace(SETUP.getThrown()));
            SETUP.addRoll(1, SETUP.getThrown());
        }
        if (SETUP.getAmountPlayers() < 3) {
            VIEW.getRollButtons(2).setVisible(false);
            //roll as cpu
            SETUP.roll();
            VIEW.getDieView(2).setImage(VIEW.getDieFace(SETUP.getThrown()));
            SETUP.addRoll(2, SETUP.getThrown());
        }
        if (SETUP.getAmountPlayers() < 4) {
            VIEW.getRollButtons(3).setVisible(false);
            //roll as cpu
            SETUP.roll();
            VIEW.getDieView(3).setImage(VIEW.getDieFace(SETUP.getThrown()));
            SETUP.addRoll(3, SETUP.getThrown());
        }
    }
}
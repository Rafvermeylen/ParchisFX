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
    private final PlayerSetup setup;
    private final OrderView view;
    private final PlayerSetupView backview;
    public OrderPresenter(
            PlayerSetup model,
            OrderView view,
            PlayerSetupView backView) {
        this.setup = model;
        this.view = view;
        this.backview = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(actionEvent -> {
            Sound.playClick();
            view.getScene().setRoot(backview);
            if (!Music.getMediaPlayer().isMute()) {
                Music.stopMusic();
                Music.playMenuMusic();
            }
        });

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            view.getRollButtons(finalI).setOnAction(actionEvent -> {
                view.getRollButtons(finalI).setVisible(false);
                Sound.playRoll();
                setup.roll();
                setup.addRoll(finalI, setup.getThrown());
                view.getDieView(finalI).setImage(view.getDieFace(setup.getThrown()));
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()) {
                    setup.order();
                    view.getStart().setVisible(true);
                }
            });
        }


        view.getStart().setOnAction(actionEvent -> {
            Sound.playClick();
            Game gameSession = new Game(setup.getPlayers());
            GameView gameView;
            try {
                gameView = new GameView();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
            view.getScene().setRoot(gameView);
        });
    }

    private void updateView() {
        for (int i = 0; i < 4; i++) {
            view.getPlayerName(i).setText(setup.getPlayers().get(i).getName());
        }

        if (setup.getAmountPlayers() < 2) {
            view.getRollButtons(1).setVisible(false);
            //roll as cpu
            setup.roll();
            view.getDieView(1).setImage(view.getDieFace(setup.getThrown()));
            setup.addRoll(1, setup.getThrown());
        }
        if (setup.getAmountPlayers() < 3) {
            view.getRollButtons(2).setVisible(false);
            //roll as cpu
            setup.roll();
            view.getDieView(2).setImage(view.getDieFace(setup.getThrown()));
            setup.addRoll(2, setup.getThrown());
        }
        if (setup.getAmountPlayers() < 4) {
            view.getRollButtons(3).setVisible(false);
            //roll as cpu
            setup.roll();
            view.getDieView(3).setImage(view.getDieFace(setup.getThrown()));
            setup.addRoll(3, setup.getThrown());
        }
    }
}
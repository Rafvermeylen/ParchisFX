package kdg.be.parchis.views.endgamescreen;

import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.musiclogic.Music;
import kdg.be.parchis.views.mainmenu.MainMenuPresenter;
import kdg.be.parchis.views.mainmenu.MainMenuView;

import java.io.FileNotFoundException;

public class EndgameScreenPresenter {
    private final Game gamesession;
    private final EndgameScreenView view;

    public EndgameScreenPresenter(
            Game model,
            EndgameScreenView view) {
        this.gamesession = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBackToMenu().setOnAction(event -> {
            try {
                MainMenuView menuView = new MainMenuView();
                MainMenuPresenter presenter = new MainMenuPresenter(menuView);
                if (!Music.getMediaPlayer().isMute()) {
                    Music.stopMusic();
                    Music.muteMenuMusic();
                }
                view.getScene().setRoot(menuView);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void updateView() {
        if (gamesession.getWinner() == null) {
            view.getScoreWinner().setText("Aww, you didn't win. Better luck next time!");
        } else {
            view.getScoreWinner().setText("Well played, " + gamesession.getWinner().name() +
                    "!\nYour score was: " + gamesession.getWinner().score());
        }
    }
}

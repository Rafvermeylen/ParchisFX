package kdg.be.parchis.views.endgamescreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.views.mainmenu.MainMenuPresenter;
import kdg.be.parchis.views.mainmenu.MainMenuView;

import java.io.FileNotFoundException;

public class EndgameScreenPresenter {
    private final Game GAMESESSION;
    private final EndgameScreenView VIEW;

    public EndgameScreenPresenter(
            Game model,
            EndgameScreenView view) {
        this.GAMESESSION = model;
        this.VIEW = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        VIEW.getBackToMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MainMenuView menuView = new MainMenuView();
                    MainMenuPresenter presenter = new MainMenuPresenter(menuView);
                    if (!Music.getMediaPlayer().isMute()) {
                        Music.stopMusic();
                        Music.muteMenuMusic();
                    }
                    VIEW.getScene().setRoot(menuView);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void updateView() {
        if (GAMESESSION.getWinner() == null) {
            VIEW.getScoreWinner().setText("Aww, you didn't win. Better luck next time!");
        } else {
            VIEW.getScoreWinner().setText("Well played, " + GAMESESSION.getWinner().name() +
                    "!\nYour score was: " + GAMESESSION.getWinner().score());
        }
    }
}

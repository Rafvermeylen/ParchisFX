package kdg.be.parchis.views.endgamescreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.views.mainmenu.MainMenuPresenter;
import kdg.be.parchis.views.mainmenu.MainMenuView;

import java.io.FileNotFoundException;

public class EndgameScreenPresenter {
    private Game gameSession;
    private EndgameScreenView view;
    public EndgameScreenPresenter(
            Game model,
            EndgameScreenView view) {
        this.gameSession = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBackToMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MainMenuView menuView = new MainMenuView();
                    MainMenuPresenter presenter = new MainMenuPresenter(menuView);
                    if (!Music.getMediaPlayer().isMute()){
                        Music.stopMusic();
                        Music.muteMenuMusic();
                    }
                    view.getScene().setRoot(menuView);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void updateView() {
        if (gameSession.getWinner() == null){
            view.getScoreWinner().setText("Aww, you didn't win. Better luck next time!");
        } else {
            view.getScoreWinner().setText("Well played, " + gameSession.getWinner().getName() + "!\nYour score was: " + gameSession.getWinner().getScore());
        }
    }
    public void addWindowEventHandlers () {
    }
}

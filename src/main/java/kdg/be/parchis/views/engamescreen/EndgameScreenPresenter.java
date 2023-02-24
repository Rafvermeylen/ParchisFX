package kdg.be.parchis.views.engamescreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.game.Game;
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
                    view.getScene().setRoot(menuView);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    MainMenuPresenter presenter = new MainMenuPresenter(new MainMenuView());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    private void updateView() {
        view.getScoreWinner().setText("Well played, " + gameSession.getWinner().getName() + "!\nYour score was: " + gameSession.getWinner().getScore());
    }
    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}

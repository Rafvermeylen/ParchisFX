package kdg.be.parchis.views.mainmenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.views.leaderboards.LeaderboardPresenter;
import kdg.be.parchis.views.leaderboards.LeaderboardView;
import kdg.be.parchis.model.Cheats;
import kdg.be.parchis.views.playerselect.PlayerSelectPresenter;
import kdg.be.parchis.views.playerselect.PlayerSelectView;


import java.io.FileNotFoundException;

public class MainMenuPresenter {
    private Cheats cheats;
    private MainMenuView view;
    public MainMenuPresenter(
            Cheats cheats,
            MainMenuView view) {
        this.cheats = cheats;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
// Koppelt event handlers (anon. inner klassen)
// aan de controls uit de view.
// Event handlers: roepen methodes aan uit het
// model en zorgen voor een update van de view.
        view.getExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        view.getCheat().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cheats.clickButton();
                if (cheats.getActivated()){
                    view.getCheat().setText("Cheats: ON");
                } else {
                    view.getCheat().setText("Cheats: OFF");
                }
            }
        });
        view.getLeaderboards().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LeaderboardView leadView = null;
                try {
                    leadView = new LeaderboardView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                LeaderboardPresenter leadPres = new LeaderboardPresenter(leadView.getLeaderboards(),leadView, view);
                view.getScene().setRoot(leadView);
            }
        });
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSelectView psView = null;
                try {
                    psView = new PlayerSelectView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                PlayerSelectPresenter psPresenter = new PlayerSelectPresenter(psView);
                view.getScene().setRoot(psView);
            }
        });
    }
    private void updateView() {
// Vult de view met data uit model
    }
    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
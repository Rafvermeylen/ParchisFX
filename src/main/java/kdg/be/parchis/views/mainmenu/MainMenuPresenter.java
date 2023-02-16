package kdg.be.parchis.views.mainmenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import kdg.be.parchis.model.menu.Cheats;
import kdg.be.parchis.model.musicLogic.SoundClass;
import kdg.be.parchis.views.leaderboards.LeaderboardPresenter;
import kdg.be.parchis.views.leaderboards.LeaderboardView;
import kdg.be.parchis.views.playerselect.PlayerSelectPresenter;
import kdg.be.parchis.views.playerselect.PlayerSelectView;

import java.io.File;
import java.io.FileNotFoundException;

public class MainMenuPresenter {
    private MainMenuView view;
    private final KeyCode fullscreenKey = KeyCode.F;
    private final KeyCode cheatKey = KeyCode.C;

    public MainMenuPresenter(MainMenuView view) {
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
                SoundClass.playClick();
                Cheats.clickButton();
                updateView();
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
                LeaderboardPresenter leadPres = new LeaderboardPresenter(leadView, view);
                SoundClass.playClick();
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
                PlayerSelectPresenter psPresenter = new PlayerSelectPresenter(psView, view);
                SoundClass.playClick();
                view.getScene().setRoot(psView);
            }
        });
    }

    private void updateView() {
// Vult de view met data uit model
        if (Cheats.getActivated()) {
            view.getCheat().setText("Cheats: ON");
        } else {
            view.getCheat().setText("Cheats: OFF");
        }
    }

    public void addWindowEventHandlers() {
        //make game fullscreen by pressing f
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == fullscreenKey) {
                Scene scene = view.getScene();
                if (scene != null) {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setFullScreen(!stage.isFullScreen());
                }
            }
        });
        //confirm window alert
/*
        Window window = view.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit program");
                alert.setHeaderText("Quiting already?");
                alert.setContentText("Are you sure you want to quit?");

                Optional<ButtonType> choice = alert.showAndWait();

                if (choice.isPresent() && choice.get() == ButtonType.OK) {
                    Platform.exit();
                } else {
                    event.consume();
                }
            }
        });

 */
    }
}
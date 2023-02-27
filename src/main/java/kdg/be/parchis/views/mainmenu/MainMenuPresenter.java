package kdg.be.parchis.views.mainmenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import kdg.be.parchis.model.menu.Cheats;
import kdg.be.parchis.model.musicLogic.MainMusic;
import kdg.be.parchis.model.musicLogic.SoundClass;
import kdg.be.parchis.views.credits.CreditsPresenter;
import kdg.be.parchis.views.credits.CreditsView;
import kdg.be.parchis.views.leaderboards.LeaderboardPresenter;
import kdg.be.parchis.views.leaderboards.LeaderboardView;
import kdg.be.parchis.views.playerselect.PlayerSelectPresenter;
import kdg.be.parchis.views.playerselect.PlayerSelectView;

import java.io.FileNotFoundException;

public class MainMenuPresenter {
    private MainMenuView view;
    private final KeyCode fullscreenKey = KeyCode.F;

    public MainMenuPresenter(MainMenuView view) {
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        view.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMusic.muteMenuMusic();

                if (MainMusic.getMediaPlayer().isMute()) {
                    view.getMusicPic().setImage(view.getMusicMute());
                } else {
                    view.getMusicPic().setImage(view.getMusicLoud());
                }
            }
        });

        view.getFxButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundClass.clickMute();

                if (SoundClass.isMuted()) {
                    view.getSoundPic().setImage(view.getSfxMute());
                } else {
                    view.getSoundPic().setImage(view.getSfxLoud());
                }
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

        view.getCredits().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreditsView creditsView = null;
                try {
                    creditsView = new CreditsView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                CreditsPresenter creditsPresenter = new CreditsPresenter(creditsView, view);
                SoundClass.playClick();
                view.getScene().setRoot(creditsView);
            }
        });
    }

    private void updateView() {
        if (Cheats.getActivated()) {
            view.getCheat().setText("Cheats: ON");
        } else {
            view.getCheat().setText("Cheats: OFF");
        }
        updateIcons();
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
    }

    public void updateIcons(){
        if (MainMusic.getMediaPlayer().isMute()){
            view.getMusicPic().setImage(view.getMusicMute());
        } else {
            view.getMusicPic().setImage(view.getMusicLoud());
        }
        if (SoundClass.isMuted()){
            view.getSoundPic().setImage(view.getSfxMute());
        } else {
            view.getSoundPic().setImage(view.getSfxLoud());
        }
    }

}
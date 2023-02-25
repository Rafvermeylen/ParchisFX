package kdg.be.parchis.views.leaderboards;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import kdg.be.parchis.model.musicLogic.MainMusic;
import kdg.be.parchis.model.musicLogic.SoundClass;
import kdg.be.parchis.views.mainmenu.MainMenuView;

import java.io.File;

public class LeaderboardPresenter {
    private LeaderboardView view;
    private MainMenuView backView;
    private Image musicMute;
    private Button soundButton;

    private final KeyCode fullscreenKey = KeyCode.F;


    public LeaderboardPresenter(LeaderboardView view, MainMenuView backView) {
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
// Koppelt event handlers (anon. inner klassen)
// aan de controls uit de view.
// Event handlers: roepen methodes aan uit het
// model en zorgen voor een update van de view.
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundClass.playClick();
                view.getScene().setRoot(backView);
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
    }
    private void updateView() {
        if (MainMusic.getMediaPlayer().isMute()){
            view.getMusicPic().setImage(view.getMusicMute());
        } else {
            view.getMusicPic().setImage(view.getMusicLoud());
        }
    }
    public void addWindowEventHandlers () {
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == fullscreenKey) {
                Scene scene = view.getScene();
                if (scene != null) {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setFullScreen(!stage.isFullScreen());
                }
            }
        });
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
package kdg.be.parchis.views.credits;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.musiclogic.Music;
import kdg.be.parchis.model.musiclogic.Sound;
import kdg.be.parchis.views.mainmenu.MainMenuView;

public class CreditsPresenter {
    private final CreditsView VIEW;
    private final MainMenuView BACKVIEW;


    public CreditsPresenter(CreditsView view, MainMenuView backView) {
        this.VIEW = view;
        this.BACKVIEW = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        VIEW.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                VIEW.getScene().setRoot(BACKVIEW);
            }
        });

        VIEW.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Music.muteMenuMusic();
                if (Music.getMediaPlayer().isMute()) {
                    VIEW.getMusicPic().setImage(VIEW.getMusicMute());
                } else {
                    VIEW.getMusicPic().setImage(VIEW.getMusicLoud());
                }
            }
        });

        VIEW.getFxButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.clickMute();

                if (Sound.isMuted()) {
                    VIEW.getSoundPic().setImage(VIEW.getSfxMute());
                } else {
                    VIEW.getSoundPic().setImage(VIEW.getSfxLoud());
                }
            }
        });
    }

    private void updateView() {
        if (Music.getMediaPlayer().isMute()) {
            VIEW.getMusicPic().setImage(VIEW.getMusicMute());
        } else {
            VIEW.getMusicPic().setImage(VIEW.getMusicLoud());
        }
        if (Sound.isMuted()) {
            VIEW.getSoundPic().setImage(VIEW.getSfxMute());
        } else {
            VIEW.getSoundPic().setImage(VIEW.getSfxLoud());
        }
    }
}


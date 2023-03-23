package kdg.be.parchis.views.rules;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.mainmenu.MainMenuView;

public class RulePresenter {
    private final RuleView VIEW;
    private final MainMenuView BACKVIEW;

    public RulePresenter(RuleView view, MainMenuView backView) {
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

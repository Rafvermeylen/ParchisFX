package kdg.be.parchis.views.rules;

import kdg.be.parchis.model.musiclogic.Music;
import kdg.be.parchis.model.musiclogic.Sound;
import kdg.be.parchis.views.mainmenu.MainMenuView;

public class RulePresenter {
    private final RuleView view;
    private final MainMenuView backview;

    public RulePresenter(RuleView view, MainMenuView backView) {
        this.view = view;
        this.backview = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBack().setOnAction(actionEvent -> {
            Sound.playClick();
            view.getScene().setRoot(backview);
        });

        view.getSoundButton().setOnAction(actionEvent -> {
            Music.muteMenuMusic();
            if (Music.getMediaPlayer().isMute()) {
                view.getMusicPic().setImage(view.getMusicMute());
            } else {
                view.getMusicPic().setImage(view.getMusicLoud());
            }
        });

        view.getFxButton().setOnAction(actionEvent -> {
            Sound.clickMute();

            if (Sound.isMuted()) {
                view.getSoundPic().setImage(view.getSfxMute());
            } else {
                view.getSoundPic().setImage(view.getSfxLoud());
            }
        });
    }

    private void updateView() {
        if (Music.getMediaPlayer().isMute()) {
            view.getMusicPic().setImage(view.getMusicMute());
        } else {
            view.getMusicPic().setImage(view.getMusicLoud());
        }
        if (Sound.isMuted()) {
            view.getSoundPic().setImage(view.getSfxMute());
        } else {
            view.getSoundPic().setImage(view.getSfxLoud());
        }
    }
}

package kdg.be.parchis.views.playerselect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musiclogic.Music;
import kdg.be.parchis.model.musiclogic.Sound;
import kdg.be.parchis.views.mainmenu.MainMenuView;
import kdg.be.parchis.views.playersetup.PlayerSetupPresenter;
import kdg.be.parchis.views.playersetup.PlayerSetupView;

import java.io.FileNotFoundException;

public class PlayerSelectPresenter {
    private final PlayerSelectView view;
    private final MainMenuView backview;

    public PlayerSelectPresenter(
            PlayerSelectView view, MainMenuView backView) {
        this.view = view;
        this.backview = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

        for (int i = 0; i < view.getPlayers().size(); i++) {
            int finalI = i + 1;
            view.getPlayers().get(i).setOnAction(actionEvent -> {
                PlayerSetup playerSetup = new PlayerSetup(finalI);
                selectedPlayers(playerSetup);
            });
        }

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

    public void selectedPlayers(PlayerSetup playerSetup) {
        PlayerSetupView psView;
        try {
            psView = new PlayerSetupView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PlayerSetupPresenter psPres = new PlayerSetupPresenter(playerSetup, psView, view);
        Sound.playClick();
        view.getScene().setRoot(psView);
    }
}
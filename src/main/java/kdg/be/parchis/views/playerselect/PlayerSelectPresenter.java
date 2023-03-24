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
    private final PlayerSelectView VIEW;
    private final MainMenuView BACKVIEW;

    public PlayerSelectPresenter(
            PlayerSelectView view, MainMenuView backView) {
        this.VIEW = view;
        this.BACKVIEW = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

        for (int i = 0; i < VIEW.getPlayers().size(); i++) {
            int finalI = i + 1;
            VIEW.getPlayers().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    PlayerSetup playerSetup = new PlayerSetup(finalI);
                    selectedPlayers(playerSetup);
                }
            });
        }

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

    public void selectedPlayers(PlayerSetup playerSetup) {
        PlayerSetupView psView;
        try {
            psView = new PlayerSetupView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PlayerSetupPresenter psPres = new PlayerSetupPresenter(playerSetup, psView, VIEW);
        Sound.playClick();
        VIEW.getScene().setRoot(psView);
    }
}
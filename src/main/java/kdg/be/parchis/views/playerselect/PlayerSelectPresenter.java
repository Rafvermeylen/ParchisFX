package kdg.be.parchis.views.playerselect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.mainmenu.MainMenuView;
import kdg.be.parchis.views.playersetup.PlayerSetupPresenter;
import kdg.be.parchis.views.playersetup.PlayerSetupView;

import java.io.FileNotFoundException;

public class PlayerSelectPresenter {
    private final PlayerSelectView view;
    private final MainMenuView backView;

    public PlayerSelectPresenter(
            PlayerSelectView view, MainMenuView backView) {
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getOnePlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(1);
                selectedPlayers(playerSetup);
            }
        });

        view.getTwoPlayers().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(2);
                selectedPlayers(playerSetup);
            }
        });

        view.getThreePlayers().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(3);
                selectedPlayers(playerSetup);
            }
        });

        view.getFourPlayers().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(4);
                selectedPlayers(playerSetup);
            }
        });

        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                view.getScene().setRoot(backView);
            }
        });

        view.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Music.muteMenuMusic();
                if (Music.getMediaPlayer().isMute()) {
                    view.getMusicPic().setImage(view.getMusicMute());
                } else {
                    view.getMusicPic().setImage(view.getMusicLoud());
                }
            }
        });

        view.getFxButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.clickMute();

                if (Sound.isMuted()) {
                    view.getSoundPic().setImage(view.getSfxMute());
                } else {
                    view.getSoundPic().setImage(view.getSfxLoud());
                }
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
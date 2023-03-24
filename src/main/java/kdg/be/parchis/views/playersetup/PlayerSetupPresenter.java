package kdg.be.parchis.views.playersetup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musiclogic.Music;
import kdg.be.parchis.model.musiclogic.Sound;
import kdg.be.parchis.views.order.OrderPresenter;
import kdg.be.parchis.views.order.OrderView;
import kdg.be.parchis.views.playerselect.PlayerSelectView;

import java.io.FileNotFoundException;

public class PlayerSetupPresenter extends BorderPane {
    private final PlayerSetup SETUP;
    private final PlayerSetupView VIEW;
    private final PlayerSelectView BACKVIEW;

    public PlayerSetupPresenter(
            PlayerSetup model,
            PlayerSetupView view,
            PlayerSelectView backView) {
        this.SETUP = model;
        this.VIEW = view;
        this.BACKVIEW = backView;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        VIEW.getBack().setOnAction(actionEvent -> {
            Sound.playClick();
            VIEW.getScene().setRoot(BACKVIEW);
        });

        VIEW.getPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.playClick();
                //Add players to player list
                if (SETUP.getAmountPlayers() == 1) {
                    SETUP.setPlayers(
                            VIEW.getPlayer1NameArea().getText());
                } else if (SETUP.getAmountPlayers() == 2) {
                    SETUP.setPlayers(
                            VIEW.getPlayer1NameArea().getText(),
                            VIEW.getPlayer2NameArea().getText());
                } else if (SETUP.getAmountPlayers() == 3) {
                    SETUP.setPlayers(
                            VIEW.getPlayer1NameArea().getText(),
                            VIEW.getPlayer2NameArea().getText(),
                            VIEW.getPlayer3NameArea().getText());
                } else if (SETUP.getAmountPlayers() == 4) {
                    SETUP.setPlayers(
                            VIEW.getPlayer1NameArea().getText(),
                            VIEW.getPlayer2NameArea().getText(),
                            VIEW.getPlayer3NameArea().getText(),
                            VIEW.getPlayer4NameArea().getText());
                }
                //change view
                OrderView orderview = null;
                try {
                    orderview = new OrderView();
                } catch (FileNotFoundException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                OrderPresenter orderPresenter = new OrderPresenter(SETUP, orderview, VIEW);
                VIEW.getScene().setRoot(orderview);
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
        if (SETUP.getAmountPlayers() == 1) {
            VIEW.getSetupNames().getChildren().add(VIEW.getPlayer1NameArea());
        } else if (SETUP.getAmountPlayers() == 2) {
            VIEW.getSetupNames().getChildren().addAll(VIEW.getPlayer1NameArea(), VIEW.getPlayer2NameArea());
        } else if (SETUP.getAmountPlayers() == 3) {
            VIEW.getSetupNames().getChildren().addAll(VIEW.getPlayer1NameArea(), VIEW.getPlayer2NameArea(), VIEW.getPlayer3NameArea());
        } else if (SETUP.getAmountPlayers() == 4) {
            VIEW.getSetupNames().getChildren().addAll(VIEW.getPlayer1NameArea(), VIEW.getPlayer2NameArea(), VIEW.getPlayer3NameArea(), VIEW.getPlayer4NameArea());
        }
    }

    public void addWindowEventHandlers() {
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

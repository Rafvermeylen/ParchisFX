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
    private final PlayerSetup setup;
    private final PlayerSetupView view;
    private final PlayerSelectView backview;

    public PlayerSetupPresenter(
            PlayerSetup model,
            PlayerSetupView view,
            PlayerSelectView backView) {
        this.setup = model;
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

        view.getPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.playClick();
                //Add players to player list
                if (setup.getAmountPlayers() == 1) {
                    setup.setPlayers(
                            view.getPlayer1NameArea().getText());
                } else if (setup.getAmountPlayers() == 2) {
                    setup.setPlayers(
                            view.getPlayer1NameArea().getText(),
                            view.getPlayer2NameArea().getText());
                } else if (setup.getAmountPlayers() == 3) {
                    setup.setPlayers(
                            view.getPlayer1NameArea().getText(),
                            view.getPlayer2NameArea().getText(),
                            view.getPlayer3NameArea().getText());
                } else if (setup.getAmountPlayers() == 4) {
                    setup.setPlayers(
                            view.getPlayer1NameArea().getText(),
                            view.getPlayer2NameArea().getText(),
                            view.getPlayer3NameArea().getText(),
                            view.getPlayer4NameArea().getText());
                }
                //change view
                OrderView orderview = null;
                try {
                    orderview = new OrderView();
                } catch (FileNotFoundException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                OrderPresenter orderPresenter = new OrderPresenter(setup, orderview, view);
                view.getScene().setRoot(orderview);
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
        if (setup.getAmountPlayers() == 1) {
            view.getSetupNames().getChildren().add(view.getPlayer1NameArea());
        } else if (setup.getAmountPlayers() == 2) {
            view.getSetupNames().getChildren().addAll(view.getPlayer1NameArea(), view.getPlayer2NameArea());
        } else if (setup.getAmountPlayers() == 3) {
            view.getSetupNames().getChildren().addAll(view.getPlayer1NameArea(), view.getPlayer2NameArea(), view.getPlayer3NameArea());
        } else if (setup.getAmountPlayers() == 4) {
            view.getSetupNames().getChildren().addAll(view.getPlayer1NameArea(), view.getPlayer2NameArea(), view.getPlayer3NameArea(), view.getPlayer4NameArea());
        }
    }

    public void addWindowEventHandlers() {
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

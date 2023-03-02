package kdg.be.parchis.views.playersetup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musicLogic.MainMusic;
import kdg.be.parchis.model.musicLogic.SoundClass;
import kdg.be.parchis.views.order.OrderPresenter;
import kdg.be.parchis.views.order.OrderView;
import kdg.be.parchis.views.playerselect.PlayerSelectView;

import java.io.FileNotFoundException;

public class PlayerSetupPresenter extends BorderPane {
    private PlayerSetup setup;
    private PlayerSetupView view;
    private PlayerSelectView backView;
    private final KeyCode fullscreenKey = KeyCode.F;

    public PlayerSetupPresenter (
            PlayerSetup model,
            PlayerSetupView view,
            PlayerSelectView backView) {
        this.setup = model;
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBack().setOnAction(actionEvent -> {
            SoundClass.playClick();
            view.getScene().setRoot(backView);
        });
        view.getPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SoundClass.playClick();
                //Add players to playerlist
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
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                OrderPresenter orderPresenter = new OrderPresenter(setup, orderview, view);
                view.getScene().setRoot(orderview);
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
        if (setup.getAmountPlayers()==1){
            view.getSetupNames().getChildren().add(view.getPlayer1NameArea());
        } else if (setup.getAmountPlayers()==2){
            view.getSetupNames().getChildren().addAll(view.getPlayer1NameArea(),view.getPlayer2NameArea());
        }else if (setup.getAmountPlayers()==3){
            view.getSetupNames().getChildren().addAll(view.getPlayer1NameArea(), view.getPlayer2NameArea(),view.getPlayer3NameArea());
        }else if (setup.getAmountPlayers()==4){
            view.getSetupNames().getChildren().addAll(view.getPlayer1NameArea(),view.getPlayer2NameArea(),view.getPlayer3NameArea(),view.getPlayer4NameArea());
        }
    }
    public void addWindowEventHandlers() {
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == fullscreenKey) {
                Scene scene = view.getScene();
                if (scene != null) {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setFullScreen(!stage.isFullScreen());
                }
            }
        });

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

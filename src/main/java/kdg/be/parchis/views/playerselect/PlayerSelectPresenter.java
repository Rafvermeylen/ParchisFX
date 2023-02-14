package kdg.be.parchis.views.playerselect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import kdg.be.parchis.model.MusicLogic;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.views.mainmenu.MainMenuView;
import kdg.be.parchis.views.playersetup.PlayerSetupPresenter;
import kdg.be.parchis.views.playersetup.PlayerSetupView;

import java.io.File;
import java.io.FileNotFoundException;

public class PlayerSelectPresenter {
    private PlayerSelectView view;
    private MainMenuView backView;
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
                String path = "resources\\audio\\click.mp3";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                sound.play();
                view.getScene().setRoot(backView);
            }
        });
    }
    private void updateView() {
// Vult de view met data uit model
    }
    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }

    public void selectedPlayers(PlayerSetup playerSetup) {
        PlayerSetupView psView;
        try {
            psView = new PlayerSetupView(playerSetup);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PlayerSetupPresenter psPres = new PlayerSetupPresenter(playerSetup, psView, view);
        String path = "resources\\audio\\click.mp3";
        Media media = new Media(new File(path).toURI().toString());
        // Just media doesn't work, x.getSource has to be added as well.
        AudioClip sound = new AudioClip(media.getSource());
        sound.play();
        view.getScene().setRoot(psView);
    }
}
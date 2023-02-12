package kdg.be.parchis.views.playerselect;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
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
        //this.model = model;
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
// Koppelt event handlers (anon. inner klassen)
// aan de controls uit de view.
// Event handlers: roepen methodes aan uit het
// model en zorgen voor een update van de view.
        view.getOnePlayer().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(1);
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
        });
        view.getTwoPlayers().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(2);
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
        });
        view.getThreePlayers().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(3);
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
        });
        view.getFourPlayers().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayerSetup playerSetup = new PlayerSetup(4);
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
}